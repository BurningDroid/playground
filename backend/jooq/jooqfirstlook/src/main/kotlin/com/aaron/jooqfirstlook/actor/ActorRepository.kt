package com.aaron.jooqfirstlook.actor

import com.aaron.jooqfirstlook.utils.jooq.inIfNotEmpty
import org.jooq.Condition
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.Field
import org.jooq.generated.tables.JActor
import org.jooq.generated.tables.JFilm
import org.jooq.generated.tables.JFilmActor
import org.jooq.generated.tables.daos.ActorDao
import org.jooq.generated.tables.pojos.Actor
import org.jooq.generated.tables.pojos.Film
import org.jooq.impl.DSL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class ActorRepository(
    configuration: Configuration
) {
    @Autowired
    lateinit var dslContext: DSLContext

    private val actorDao: ActorDao = ActorDao(configuration)

    private val ACTOR: JActor = JActor.ACTOR

    fun findByFirstNameAndLastName(firstName: String, lastName: String): List<Actor> {
        return dslContext.selectFrom(ACTOR)
            .where(
                ACTOR.FIRST_NAME.eq(firstName),
                ACTOR.LAST_NAME.eq(lastName)
            )
            .fetchInto(Actor::class.java)
    }

    fun findByFirstNameOrLastName(firstName: String, lastName: String): List<Actor> {
        return dslContext.selectFrom(ACTOR)
            .where(
                ACTOR.FIRST_NAME.eq(firstName).or(ACTOR.LAST_NAME.eq(lastName))
            )
            .fetchInto(Actor::class.java)
    }

    fun findByActorIdIn(ids: List<Long>?): List<Actor> {
        return dslContext.selectFrom(ACTOR)
            .where(inIfNotEmpty(ACTOR.ACTOR_ID, ids))
            .fetchInto(Actor::class.java)
    }

    fun findActorFilmography(searchOption: ActorFilmographySearchOption): List<ActorFilmography> {
        val FILM_ACTOR = JFilmActor.FILM_ACTOR
        val FILM = JFilm.FILM

        val actorListMap = dslContext.select(
            ACTOR.`as`("actor"),
            FILM.`as`("film")
        ).from(ACTOR)
            .join(FILM_ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
            .join(FILM).on(FILM.FILM_ID.eq(FILM_ACTOR.FILM_ID))
            .where(
                containsIfNotBlank(ACTOR.FIRST_NAME.concat(" ").concat(ACTOR.LAST_NAME), searchOption.actorName),
                containsIfNotBlank(FILM.TITLE, searchOption.filmTitle)
            )
            .fetchGroups({ record ->
                record.get("actor", Actor::class.java)
            }, { record ->
                record.get("film", Film::class.java)
            })

        return actorListMap.entries.map { entry ->
            ActorFilmography(entry.key, entry.value)
        }.toList()
    }

    private fun containsIfNotBlank(field: Field<String?>, value: String): Condition {
        if (value.isBlank()) {
            return DSL.noCondition()
        }

        return field.like("%$value%")
    }


}