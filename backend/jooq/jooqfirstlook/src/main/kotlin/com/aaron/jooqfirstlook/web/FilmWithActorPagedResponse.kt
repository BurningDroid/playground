package com.aaron.jooqfirstlook.web

import com.aaron.jooqfirstlook.film.FilmWithActor

data class FilmWithActorPagedResponse(
    val page: PagedResponse,
    val filmActorList: List<FilmActorResponse>
) {
    companion object {
        fun of(page: PagedResponse, filmActorList: List<FilmWithActor>) = FilmWithActorPagedResponse(
            page = page,
            filmActorList = filmActorList.map(FilmActorResponse::of),
        )
    }
}

data class FilmActorResponse(
    val filmTitle: String,
    val actorFullName: String,
    val filmId: Long
) {
    companion object {
        fun of(filmWithActor: FilmWithActor) = FilmActorResponse(
            filmTitle = filmWithActor.title,
            actorFullName = filmWithActor.actorFullName,
            filmId = filmWithActor.filmId
        )
    }
}