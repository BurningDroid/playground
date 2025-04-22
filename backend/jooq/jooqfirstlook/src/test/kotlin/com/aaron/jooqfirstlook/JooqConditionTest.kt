package com.aaron.jooqfirstlook

import com.aaron.jooqfirstlook.actor.ActorFilmography
import com.aaron.jooqfirstlook.actor.ActorFilmographySearchOption
import com.aaron.jooqfirstlook.actor.ActorRepository
import org.assertj.core.api.Assertions.assertThat
import org.jooq.generated.tables.pojos.Actor
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JooqConditionTest {

	@Autowired
	lateinit var actorRepository: ActorRepository

	@Test
	fun `AND test`() {
		val firstName = "ED"
		val lastName = "CHASE"

		val result: List<Actor> = actorRepository.findByFirstNameAndLastName(firstName, lastName)

		assertThat(result).hasSize(1)
	}

	@Test
	fun `OR test`() {
		val firstName = "ED"
		val lastName = "CHASE"

		val result: List<Actor> = actorRepository.findByFirstNameOrLastName(firstName, lastName)

		assertThat(result).hasSizeGreaterThan(1)
	}

	@Test
	fun `IN test - null`() {
		val result: List<Actor> = actorRepository.findByActorIdIn(null)

		assertThat(result).hasSizeGreaterThan(1)
	}

	@Test
	fun `IN test - empty`() {
		val result: List<Actor> = actorRepository.findByActorIdIn(listOf())

		assertThat(result).hasSizeGreaterThan(1)
	}

	@Test
	fun `IN test`() {
		val result: List<Actor> = actorRepository.findByActorIdIn(listOf(1))

		assertThat(result).hasSize(1)
	}

	@Test
	fun `search test1`() {
		val searchOption = ActorFilmographySearchOption("LOLLOBRIGIDA", "")

		val result: List<ActorFilmography> = actorRepository.findActorFilmography(searchOption)

		assertThat(result).hasSize(1)
	}

	@Test
	fun `search test2`() {
		val searchOption = ActorFilmographySearchOption("LOLLOBRIGIDA", "COMMANDMENTS EXPRESS")

		val result: List<ActorFilmography> = actorRepository.findActorFilmography(searchOption)

		assertThat(result).hasSize(1)
		assertThat(result[0].filmList).hasSize(1)
	}
}
