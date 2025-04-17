package com.example.kotlin.jooq.dto.user

import com.example.generated.tables.pojos.Users
import com.example.kotlin.jooq.exceptions.NotFoundException

data class UserResponse(
    val id: Long,
    val email: String,
    val username: String
)

fun Users.toUserResponse(): UserResponse = UserResponse(
    id = id ?: throw NotFoundException("id does not exist"),
    email = email ?: throw NotFoundException("email does not exist"),
    username = username ?: throw NotFoundException("username does not exist"),
)
