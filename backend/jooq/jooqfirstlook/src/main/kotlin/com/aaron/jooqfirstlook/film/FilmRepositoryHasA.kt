package com.aaron.jooqfirstlook.film

import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.generated.tables.JActor
import org.jooq.generated.tables.JFilm
import org.jooq.generated.tables.JFilmActor
import org.jooq.generated.tables.daos.FilmDao
import org.jooq.generated.tables.pojos.Film
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class FilmRepositoryHasA(
    configuration: Configuration
) {

    @Autowired
    lateinit var dslContext: DSLContext

    private val filmDao: FilmDao = FilmDao(configuration)

    private val FILM: JFilm = JFilm.FILM

    fun findById(id: Long): Film {
        return filmDao.findById(id) ?: throw Exception()
    }

    fun findByRangeBetween(from: Int, to: Int): List<Film> {
        return filmDao.fetchRangeOfJLength(from, to)
    }

    fun findSimpleFilmInfoById(id: Long): SimpleFilmInfo {
        return dslContext.select(
            FILM.FILM_ID,
            FILM.TITLE,
            FILM.DESCRIPTION
        ).from(FILM)
            .where(FILM.FILM_ID.eq(id))
            .fetchOneInto(SimpleFilmInfo::class.java) ?: throw Exception()
    }

    fun findFilmWithActorList(page: Long, pageSize: Long): List<FilmWithActor> {
        val FILM_ACTOR = JFilmActor.FILM_ACTOR
        val ACTOR = JActor.ACTOR

        return dslContext.select(
            FILM,
            FILM_ACTOR,
            ACTOR,
        ).from(FILM)
            .join(FILM_ACTOR).on(FILM.FILM_ID.eq(FILM_ACTOR.FILM_ID))
            .join(ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
            .offset((page - 1) * pageSize)
            .limit(pageSize)
            .fetchInto(FilmWithActor::class.java)
    }

}