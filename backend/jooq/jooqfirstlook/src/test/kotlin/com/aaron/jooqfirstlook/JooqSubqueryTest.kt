package com.aaron.jooqfirstlook

import com.aaron.jooqfirstlook.film.FilmPriceSummary
import com.aaron.jooqfirstlook.film.FilmRepository
import com.aaron.jooqfirstlook.film.FilmRepositoryHasA
import com.aaron.jooqfirstlook.film.FilmService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JooqSubqueryTest {

	@Autowired
	lateinit var filmRepository: FilmRepositoryHasA

	@Test
	fun subqueryTest() {
		val filmTitle = "EGG"

		val result: List<FilmPriceSummary> = filmRepository.findFilmPriceSummaryByFilmTitle(filmTitle)
		assertThat(result).isNotEmpty
	}
}
