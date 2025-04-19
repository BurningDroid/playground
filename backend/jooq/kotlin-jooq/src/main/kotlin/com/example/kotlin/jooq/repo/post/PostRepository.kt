package com.example.kotlin.jooq.repo.post

import com.example.generated.tables.Post_
import com.example.generated.tables.Users_
import com.example.generated.tables.pojos.Post
import com.example.generated.tables.pojos.Users
import com.example.kotlin.jooq.dto.page.PageRequest
import com.example.kotlin.jooq.dto.page.PageResponse
import com.example.kotlin.jooq.dto.post.PostWithUser
import com.example.kotlin.jooq.dto.post.PostWithUserResponse
import com.example.kotlin.jooq.exceptions.NotFoundException
import com.example.kotlin.jooq.utils.jooq.conditions.JooqConditions.likeIfNotBlank
import com.example.kotlin.jooq.utils.jooq.extensions.likeIfNotBlank
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.jooq.impl.DSL.select
import org.springframework.stereotype.Repository

@Repository
class PostRepository(
    private val dslContext: DSLContext, // JooqAutoConfiguration에 의해 생성
) {

    companion object {
        val POST: Post_ = Post_.POST
        val USERS = Users_.USERS
    }

    fun create(post: Post): Long {
        return dslContext.insertInto(
            POST,
            POST.TITLE,
            POST.CONTENT,
            POST.USER_ID,

            ).values(
            post.title,
            post.content,
            post.userId
        ).returningResult(POST.ID)
            .fetchOneInto(Long::class.java) ?: throw NotFoundException("could not create post")
    }

    fun findById(id: Long): Post {
        return dslContext.selectFrom(POST)
            .where(POST.ID.eq(id))
            .fetchOneInto(Post::class.java) ?: throw NotFoundException("could not find post")
    }

    fun findById2(id: Long): PostWithUserResponse {
        return dslContext
            .select(
                *POST.fields(),
                *USERS.fields(),
            )
            .from(POST)
            .join(USERS).on(POST.USER_ID.eq(USERS.ID))
            .where(POST.ID.eq(id))
            .fetchOne { record ->
                PostWithUser(
                    post = record.into(POST).into(Post::class.java),
                    user = record.into(USERS).into(Users::class.java)
                ).toResponse()
            } ?: throw NotFoundException("could not find post")
    }

    fun findById3(id: Long): PostWithUserResponse {
        return dslContext
            .select(
                *POST.fields(),
                *POST.users().fields()
            )
            .from(POST)
            .join(POST.users())
            .where(POST.ID.eq(id))
            .fetchOne { record ->
                PostWithUser(
                    post = record.into(POST).into(Post::class.java),
                    user = record.into(USERS).into(Users::class.java)
                ).toResponse()
            } ?: throw NotFoundException("could not find post")
    }

    fun findAll(pageRequest: PageRequest, query: String): PageResponse<PostWithUserResponse> {
        val totalElements = dslContext.select(DSL.count())
            .from(POST)
            .where(POST.TITLE.likeIfNotBlank(query).or(POST.CONTENT.likeIfNotBlank(query)))
            .fetchOneInto(Long::class.java) ?: 0L
        val content = dslContext
            .select(
                *POST.fields(),
                *USERS.fields()
            ).from(POST)
            .join(USERS).on(POST.USER_ID.eq(USERS.ID))
            .where(POST.TITLE.likeIfNotBlank(query).or(POST.CONTENT.likeIfNotBlank(query)))
            .orderBy(POST.ID.desc())
            .limit(pageRequest.size)
            .offset(pageRequest.offset())
            .fetch({ record ->
                PostWithUser(
                    post = record.into(POST).into(Post::class.java),
                    user = record.into(USERS).into(Users::class.java)
                ).toResponse()
            })

        return PageResponse.of(
            content = content,
            page = pageRequest.page,
            size = pageRequest.size,
            totalElements = totalElements
        )
    }

    fun findAll2(pageRequest: PageRequest, query: String): PageResponse<PostWithUserResponse> {
        val totalElements = dslContext.select(DSL.count())
            .from(POST)
            .where(POST.TITLE.likeIfNotBlank(query))
            .fetchOneInto(Long::class.java) ?: 0L

        val content = dslContext
            .select(
                *POST.fields(),
                *POST.users().fields()
            ).from(POST)
            .join(POST.users())
            .where(POST.TITLE.likeIfNotBlank(query))
            .orderBy(POST.ID.desc())
            .limit(pageRequest.size)
            .offset(pageRequest.offset())
            .fetch({ record ->
                PostWithUser(
                    post = record.into(POST).into(Post::class.java),
                    user = record.into(USERS).into(Users::class.java)
                ).toResponse()
            })

        return PageResponse.of(
            content = content,
            page = pageRequest.page,
            size = pageRequest.size,
            totalElements = totalElements
        )
    }

    fun findAll3(pageRequest: PageRequest, query: String): PageResponse<PostWithUserResponse> {
        val totalElements = dslContext.select(DSL.count())
            .from(POST)
            .where(POST.TITLE.likeIfNotBlank(query))
            .fetchOneInto(Long::class.java) ?: 0L

        val subquery = DSL.select(POST.ID)
            .from(POST)
            .orderBy(POST.ID.desc())
            .limit(pageRequest.size)
            .offset(pageRequest.offset())
            .asTable("subquery")

        val content = dslContext
            .select(
                *POST.fields(),
                *POST.users().fields()
            ).from(subquery)
            .leftJoin(POST).on(POST.ID.eq(subquery.field(POST.ID)))
            .join(POST.users())
            .where(POST.TITLE.likeIfNotBlank(query))
            .fetch({ record ->
                PostWithUser(
                    post = record.into(POST).into(Post::class.java),
                    user = record.into(USERS).into(Users::class.java)
                ).toResponse()
            })

        return PageResponse.of(
            content = content,
            page = pageRequest.page,
            size = pageRequest.size,
            totalElements = totalElements
        )
    }

    fun update(post: Post): Long {
        val updatedRows = dslContext.update(POST)
            .set(POST.TITLE, post.title)
            .set(POST.CONTENT, post.content)
            .where(
                POST.ID.eq(post.id),
                POST.USER_ID.eq(post.userId)
            ).execute()

        return if (updatedRows > 0) {
            return post.id!!
        } else {
            throw NotFoundException("Post with id ${post.id} not found")
        }
    }

}