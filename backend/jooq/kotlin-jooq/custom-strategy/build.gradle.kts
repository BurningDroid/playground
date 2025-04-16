plugins {
    kotlin("jvm")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

val jooqVersion: String by rootProject.extra

dependencies {
    implementation("org.jooq:jooq-codegen:$jooqVersion")
}

tasks.test {
    useJUnitPlatform()
}