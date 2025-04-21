package com.aaron.jooqfirstlook.film

import org.jooq.DSLContext
import org.jooq.generated.tables.JFilm
import org.jooq.generated.tables.pojos.Film
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

}