package com.example.kotlin.jooq.repo.post

import com.example.generated.tables.Post_
import com.example.generated.tables.daos.PostDao
import com.example.generated.tables.pojos.Post
import com.example.generated.tables.pojos.Users
import com.example.kotlin.jooq.exceptions.NotFoundException
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class PostRepository(
    private val dslContext: DSLContext, // JooqAutoConfiguration에 의해 생성
) {

    companion object {
        private val POST: Post_ = Post_.POST
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
}