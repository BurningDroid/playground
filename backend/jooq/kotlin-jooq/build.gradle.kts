buildscript {
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}
	dependencies {
		classpath("org.flywaydb:flyway-mysql:10.8.1")
		classpath("com.mysql:mysql-connector-j:8.0.33")
	}
}

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"

	id("org.flywaydb.flyway") version "10.8.1"
	id("nu.studer.jooq") version "9.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

val jooqVersion: String by extra("3.19.21")
val dbUrl = "jdbc:mysql://localhost:3308/jooq"
val dbUsername = "root"
val dbPassword = "1234"
val dbDrive = "com.mysql.cj.jdbc.Driver"

dependencies {
	// Spring Boot
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Database & Flyway
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")
	implementation("com.mysql:mysql-connector-j")

	// jooq
	jooqGenerator("com.mysql:mysql-connector-j")
	jooqGenerator("org.jooq:jooq-meta-extensions:$jooqVersion")
	jooqGenerator(project(":custom-strategy"))

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testRuntimeOnly("com.h2database:h2")
}

flyway {
	driver = dbDrive
	url = dbUrl
	user = dbUsername
	password = dbPassword
	locations = arrayOf("classpath:db/migration")
}

jooq {
	version.set(jooqVersion)
	configurations {
		create("main") {
			generateSchemaSourceOnCompilation.set(true) // 컴파일 시 코드 생성
			jooqConfiguration.apply {
				jdbc.apply { // DB 연결 설정
					driver = dbDrive
					url = dbUrl
					user = dbUsername
					password = dbPassword
				}
				generator.apply { // 코드 생성 설정
					name = "org.jooq.codegen.KotlinGenerator"
					strategy.apply {
						name = "com.example.com.example.kotlinjooq.PrefixGeneratorStrategy"
					}
					database.apply {
						name = "org.jooq.meta.mysql.MySQLDatabase"
						inputSchema = "jooq"
						includes = ".*"
						excludes = "flyway_schema_history"
					}
					generate.apply { // 생성 옵션 설정
						isDeprecated = false
						isRecords = true
						isDaos = true
						isFluentSetters = true
						isJavaTimeTypes = true
					}
					target.apply { // 생성된 코드 위치 설정
						packageName = "com.example.generated"
						directory = "build/generated/jooq/main"
					}
				}
			}
		}
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
