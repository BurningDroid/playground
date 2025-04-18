package com.example.kotlin.jooq.dto.post

import com.example.generated.tables.pojos.Post
import com.example.generated.tables.pojos.Users
import com.example.kotlin.jooq.dto.user.UserResponse
import com.example.kotlin.jooq.exceptions.NotFoundException
import java.time.LocalDateTime

data class PostResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
)

fun Post.toResponse(): PostResponse {
    return PostResponse(
        id = id ?: throw NotFoundException("post id not found"),
        title = title ?: throw NotFoundException("title not found"),
        content = content ?: throw NotFoundException("content not found"),
        createdAt = createdAt ?: throw NotFoundException("createdAt not found"),
    )
}