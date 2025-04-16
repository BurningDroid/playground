package com.example.kotlin.jooq.dto.user

import com.example.generated.tables.pojos.Users

data class UserCreateServiceDto(
    private val email: String,
    private val username: String,
    private val password: String
) {
    fun toEntity(): Users {
        return Users(
            email = email,
            username = username,
            password = password
        )
    }
}
