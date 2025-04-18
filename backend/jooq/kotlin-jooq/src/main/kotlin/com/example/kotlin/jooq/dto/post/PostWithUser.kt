package com.example.kotlin.jooq.dto.post

import com.example.generated.tables.pojos.Post
import com.example.generated.tables.pojos.Users
import com.example.kotlin.jooq.dto.user.UserResponse
import com.example.kotlin.jooq.exceptions.NotFoundException

data class PostWithUser(
    val post: Post,
    val user: Users,
) {
    val userResponse: UserResponse by lazy {
        UserResponse(
            id = user.id ?: throw NotFoundException("not found id"),
            email = user.email ?: throw NotFoundException("not found email"),
            username = user.username ?: throw NotFoundException("not found username"),
        )
    }

    val postResponse: PostResponse by lazy {
        PostResponse(
            id = post.id ?: throw NotFoundException("not found id"),
            title = post.title ?: throw NotFoundException("not found title"),
            content = post.content ?: throw NotFoundException("not found content"),
            createdAt = post.createdAt ?: throw NotFoundException("not found createdAt"),
        )
    }

    fun toResponse() = PostWithUserResponse(
        post = postResponse,
        user = userResponse
    )
}
