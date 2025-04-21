package com.aaron.jooqfirstlook.film

import com.aaron.jooqfirstlook.web.FilmWithActorPagedResponse
import com.aaron.jooqfirstlook.web.PagedResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FilmService {

    @Autowired
    lateinit var filmRepository: FilmRepository

    fun getFilmActorPageResponse(page: Long, pageSize: Long): FilmWithActorPagedResponse {
        val filmWithActorList = filmRepository.findFilmWithActorList(page, pageSize)
        return FilmWithActorPagedResponse.of(
            page = PagedResponse(page, pageSize),
            filmActorList = filmWithActorList
        )
    }
}