package com.example.kotlin.jooq.dto.post

import com.example.kotlin.jooq.dto.user.UserResponse

data class PostWithUserResponse(
    val post: PostResponse,
    val user: UserResponse
)
