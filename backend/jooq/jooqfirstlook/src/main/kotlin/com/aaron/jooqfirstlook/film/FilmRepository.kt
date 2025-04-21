package com.aaron.jooqfirstlook.film

import org.jooq.DSLContext
import org.jooq.generated.tables.JActor
import org.jooq.generated.tables.JFilm
import org.jooq.generated.tables.JFilmActor
import org.jooq.generated.tables.pojos.Film
import org.jooq.impl.DSL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class FilmRepository{

    @Autowired
    lateinit var dslContext: DSLContext

    private val FILM: JFilm = JFilm.FILM

    fun findById(id: Long): Film {
        return dslContext.select(*FILM.fields())
            .from(FILM)
            .where(FILM.FILM_ID.eq(id))
            .fetchOneInto(Film::class.java) ?: throw Exception()
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
            DSL.row(FILM.fields()),
            DSL.row(FILM_ACTOR.fields()),
            DSL.row(ACTOR.fields()),
        ).from(FILM)
            .join(FILM_ACTOR).on(FILM.FILM_ID.eq(FILM_ACTOR.FILM_ID))
            .join(ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
            .offset((page - 1) * pageSize)
            .limit(pageSize)
            .fetchInto(FilmWithActor::class.java)
    }

}