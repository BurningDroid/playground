package com.aaron.jooqfirstlook

import com.aaron.jooqfirstlook.actor.ActorRepository
import org.assertj.core.api.Assertions.assertThat
import org.jooq.DSLContext
import org.jooq.generated.tables.JActor
import org.jooq.generated.tables.pojos.Actor
import org.jooq.generated.tables.records.ActorRecord
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class JooqActiveRecordTest {

	@Autowired
	lateinit var actorRepository: ActorRepository

	@Autowired
	lateinit var dslContext: DSLContext

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
	fun `find test`() {
		val actorId = 1L

		val record: ActorRecord? = actorRepository.findRecordByActorId(actorId)

		assertThat(record).hasNoNullFieldsOrProperties()
	}

	@Test
	fun `refresh test`() {
		val actorId = 1L

		val record: ActorRecord? = actorRepository.findRecordByActorId(actorId)
		record?.firstName = null

		record?.refresh()
		record?.refresh(JActor.ACTOR.FIRST_NAME)

		assertThat(record?.firstName).isNotBlank
	}

	@Test
	fun `store test`() {
		val record = dslContext.newRecord(JActor.ACTOR)

		record.firstName = "john"
		record.lastName = "doe"
		record.store()
		record.refresh()

		assertThat(record.lastUpdate).isNotNull
	}

	@Test
	fun `update test`() {
		val actorId = 1L
		val newName = "test"
		val record: ActorRecord? = actorRepository.findRecordByActorId(actorId)
		record?.firstName = newName
		record?.store()
		record?.refresh()

		assertThat(record?.firstName).isEqualTo(newName)
	}

	@Test
	fun `delete  test`() {
		val record = dslContext.newRecord(JActor.ACTOR)
		record.firstName = "john"
		record.lastName = "doe"
		record.store()

		val result = record.delete()

		assertThat(result).isEqualTo(1)
	}
}
