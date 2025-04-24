package com.aaron.jooqfirstlook

import com.aaron.jooqfirstlook.actor.ActorRepository
import org.assertj.core.api.Assertions.assertThat
import org.jooq.generated.tables.pojos.Actor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class JooqInsertTest {

	@Autowired
	lateinit var actorRepository: ActorRepository

	lateinit var  actor: Actor

	@BeforeEach
	fun setup() {
		actor = Actor(
			firstName = "John",
			lastName = "Doe",
			lastUpdate = LocalDateTime.now()
		)
	}

	@Test
	fun insertDao() {
		assertThat(actor.actorId).isNull()

		actorRepository.saveByDao(actor)

		assertThat(actor.actorId).isNotNull()
	}

	@Test
	fun insertByRecord() {
		assertThat(actor.actorId).isNull()

		val actorRecord = actorRepository.saveByRecord(actor)

		assertThat(actorRecord.actorId).isNotNull()
		assertThat(actor.actorId).isNull()
	}

	@Test
	fun insertWithReturningPk() {
		val pk = actorRepository.saveWithReturningPkOnly(actor)

		assertThat(pk).isNotNull()
	}

	@Test
	fun insertWithReturning() {
		val actor: Actor = actorRepository.saveWithReturning(actor)

		assertThat(actor).hasNoNullFieldsOrProperties()
	}

	@Test
	fun bulkInsert() {
		val actor1 = Actor(
			firstName = "John",
			lastName = "Doe",
		)
		val actor2 = Actor(
			firstName = "John 2",
			lastName = "Doe 2",
		)
		val actors = listOf(actor1, actor2)
		actorRepository.bulkInsertWithRows(actors)
	}
}
