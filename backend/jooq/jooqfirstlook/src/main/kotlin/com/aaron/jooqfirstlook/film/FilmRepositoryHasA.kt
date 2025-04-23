package com.aaron.jooqfirstlook.film

import com.aaron.jooqfirstlook.config.converter.PriceCategoryConverter
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.generated.tables.JActor
import org.jooq.generated.tables.JFilm
import org.jooq.generated.tables.JFilmActor
import org.jooq.generated.tables.JInventory
import org.jooq.generated.tables.daos.FilmDao
import org.jooq.generated.tables.pojos.Film
import org.jooq.impl.DSL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.math.BigDecimal

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

    fun findFilmPriceSummaryByFilmTitle(filmTitle: String): List<FilmPriceSummary> {
        val INVENTORY: JInventory = JInventory.INVENTORY
        return dslContext.select(
            FILM.FILM_ID,
            FILM.TITLE,
            FILM.RENTAL_RATE,
            DSL.case_()
                .`when`(FILM.RENTAL_RATE.le(BigDecimal.valueOf(1.0)), "Cheap")
                .`when`(FILM.RENTAL_RATE.le(BigDecimal.valueOf(3.0)), "Moderate")
                .else_("Expensive").`as`("price_category").convert(PriceCategoryConverter()),
            DSL.selectCount().from(INVENTORY).where(INVENTORY.FILM_ID.eq(FILM.FILM_ID)).asField<Long>("total_inventory")
        ).from(FILM)
            .where(FILM.TITLE.like("%${filmTitle}%"))
            .fetchInto(FilmPriceSummary::class.java)
    }
}