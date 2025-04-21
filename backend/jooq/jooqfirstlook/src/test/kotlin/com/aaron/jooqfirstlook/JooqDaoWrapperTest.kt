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
	fun test() {
		val film = filmRepoIsA.findById(1)
		assertThat(film).isNotNull
	}
}
