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
import org.jooq.generated.tables.records.ActorRecord
import org.jooq.impl.DSL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class ActorRepository(
    configuration: Configuration,
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

    fun saveByDao(actor: Actor): Actor {
        actorDao.insert(actor)
        return actor
    }

    fun saveByRecord(actor: Actor): ActorRecord {
        val actorRecord = dslContext.newRecord(ACTOR, actor)
        actorRecord.insert()
        return actorRecord
    }

    fun saveWithReturningPkOnly(actor: Actor): Long {
        return dslContext.insertInto(
            ACTOR,
            ACTOR.FIRST_NAME,
            ACTOR.LAST_NAME
        ).values(actor.firstName, actor.lastName)
            .returningResult(ACTOR.ACTOR_ID)
            .fetchOneInto(Long::class.java) ?: throw Exception("")
    }

    fun saveWithReturning(actor: Actor): Actor {
        return dslContext.insertInto(
            ACTOR,
            ACTOR.FIRST_NAME,
            ACTOR.LAST_NAME
        ).values(actor.firstName, actor.lastName)
            .returning(*ACTOR.fields())
            .fetchOneInto(Actor::class.java) ?: throw Exception("")
    }

    fun bulkInsertWithRows(actors: List<Actor>) {
        val rows = actors.stream()
            .map { actor ->
                DSL.row(actor.firstName, actor.lastName)
            }
            .toList()
        dslContext.insertInto(
            ACTOR,
            ACTOR.FIRST_NAME,
            ACTOR.LAST_NAME
        ).valuesOfRows(rows).execute()
    }

    fun update(actor: Actor) {
        actorDao.update(actor)
    }

    fun findByActorId(actorId: Long?): Actor? {
        return actorDao.findById(actorId)
    }

    fun updateWithDto(actorId: Long, request: ActorUpdateRequest): Int {
        val firstName: Field<String?> =
            if (request.firstName.isNotBlank()) DSL.`val`(request.firstName) else DSL.noField(ACTOR.FIRST_NAME)
        val lastName: Field<String?> =
            if (request.lastName.isNotBlank()) DSL.`val`(request.lastName) else DSL.noField(ACTOR.LAST_NAME)
        return dslContext.update(ACTOR)
            .set(ACTOR.FIRST_NAME, firstName)
            .set(ACTOR.LAST_NAME, lastName)
            .where(ACTOR.ACTOR_ID.eq(actorId))
            .execute()
    }

    fun updateWithRecord(actorId: Long, request: ActorUpdateRequest): Int {
        val record: ActorRecord? = dslContext.fetchOne(ACTOR, ACTOR.ACTOR_ID.eq(actorId))

        if (request.firstName.isNotBlank()) {
            record?.firstName = request.firstName
        }

        if (request.lastName.isNotBlank()) {
            record?.lastName = request.lastName
        }

        return dslContext.update(ACTOR)
            .set(record)
            .where(ACTOR.ACTOR_ID.eq(actorId))
            .execute()
    }

    fun delete(actorId: Long?): Int {
        return dslContext.deleteFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.eq(actorId))
            .execute()

//        return actorDao.deleteById(actorId)
    }

    fun deleteWithRecord(actorId: Long?): Int? {
        val record = dslContext.fetchOne(ACTOR, ACTOR.ACTOR_ID.eq(actorId))
        return record?.delete()
    }


}