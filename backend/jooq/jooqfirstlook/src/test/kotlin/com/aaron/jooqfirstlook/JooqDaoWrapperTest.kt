package com.aaron.jooqfirstlook

import com.aaron.jooqfirstlook.film.FilmRepositoryHasA
import com.aaron.jooqfirstlook.film.FilmRepositoryIsA
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JooqDaoWrapperTest {

	@Autowired
	lateinit var filmRepoIsA: FilmRepositoryIsA
	@Autowired
	lateinit var filmRepoHasA: FilmRepositoryHasA

	@Test
	fun isATest() {
		val start = 100
		val end = 180

		val films = filmRepoIsA.fetchRangeOfJLength(start, end)
		assertThat(films).allSatisfy { film ->
			assertThat(film.length).isBetween(start, end)
		}
	}

	@Test
	fun hasATest() {
		val start = 100
		val end = 180

		val films = filmRepoHasA.findByRangeBetween(start, end)
		assertThat(films).allSatisfy { film ->
			assertThat(film.length).isBetween(start, end)
		}
	}
}
