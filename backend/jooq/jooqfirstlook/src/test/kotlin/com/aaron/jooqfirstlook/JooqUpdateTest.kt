package com.aaron.jooqfirstlook

import com.aaron.jooqfirstlook.actor.ActorRepository
import com.aaron.jooqfirstlook.actor.ActorUpdateRequest
import org.assertj.core.api.Assertions.assertThat
import org.jooq.generated.tables.pojos.Actor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class JooqUpdateTest {

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
	fun `update test`() {
		val newActor = actorRepository.saveWithReturning(actor)

		newActor.firstName = "Suri"
		actorRepository.update(newActor)

		val updatedActor = actorRepository.findByActorId(newActor.actorId)

		assertThat(updatedActor?.firstName).isEqualTo("Suri")
	}

	@Test
	fun `update only test`() {
		val newActorId = actorRepository.saveWithReturningPkOnly(actor)
		val request = ActorUpdateRequest(firstName = "Suri", lastName = "")

		val result = actorRepository.updateWithDto(newActorId, request)

		val updatedActor = actorRepository.findByActorId(newActorId)

		assertThat(updatedActor?.firstName).isEqualTo("Suri")
	}

	@Test
	fun `update with record test`() {
		val newActorId = actorRepository.saveWithReturningPkOnly(actor)
		val request = ActorUpdateRequest(firstName = "Suri", lastName = "")

		val result = actorRepository.updateWithRecord(newActorId, request)

		val updatedActor = actorRepository.findByActorId(newActorId)

		assertThat(updatedActor?.firstName).isEqualTo("Suri")
	}

	@Test
	fun `delete test`() {
		val newActorId = actorRepository.saveWithReturningPkOnly(actor)

		val result = actorRepository.delete(newActorId)

		assertThat(result).isEqualTo(1)
	}

	@Test
	fun `delete with record test`() {
		val newActorId = actorRepository.saveWithReturningPkOnly(actor)

		val result = actorRepository.deleteWithRecord(newActorId)

		assertThat(result).isEqualTo(1)
	}
}
