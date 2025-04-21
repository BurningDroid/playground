package com.aaron.jooqfirstlook

import com.aaron.jooqfirstlook.film.FilmRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JooqStartSelectTest {

	@Autowired
	lateinit var filmRepo: FilmRepository

	@Test
	fun test() {
		val film = filmRepo.findById(1)
		assertThat(film).isNotNull
	}

}
