package com.aaron.jooqfirstlook.film

import org.jooq.generated.tables.pojos.Actor
import org.jooq.generated.tables.pojos.Film
import org.jooq.generated.tables.pojos.FilmActor

data class FilmWithActor(
    val film: Film,
    val filmActor: FilmActor,
    val actor: Actor,
) {

    val title: String
        get() = requireNotNull(film.title)

    val actorFullName: String
        get() = "${actor.firstName} ${actor.lastName}"

    val filmId: Long
        get() = requireNotNull(film.filmId)
}