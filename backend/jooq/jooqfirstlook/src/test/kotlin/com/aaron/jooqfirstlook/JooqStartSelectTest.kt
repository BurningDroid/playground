package com.aaron.jooqfirstlook

import com.aaron.jooqfirstlook.film.FilmRepository
import com.aaron.jooqfirstlook.film.FilmService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JooqStartSelectTest {

	@Autowired
	lateinit var filmRepo: FilmRepository

	@Autowired
	lateinit var filmService: FilmService

	@Test
	fun test() {
		val film = filmRepo.findById(1)
		assertThat(film).isNotNull
	}

	@Test
	fun test2() {
		val film = filmRepo.findSimpleFilmInfoById(1)
		assertThat(film).isNotNull
		assertThat(film).hasNoNullFieldsOrProperties()
	}

	@Test
	fun test3() {
		val filmActorPageResp = filmService.getFilmActorPageResponse(1, 20)
		assertThat(filmActorPageResp.filmActorList).hasSize(20)
	}
}
