package com.example.kotlin.jooq.dto.user

data class UserRequest(
    private val id: Long? = null,
    private val email: String,
    private val username: String,
    private val password: String
) {
    fun toCreateServiceDto(): UserCreateServiceDto {
        return UserCreateServiceDto(
            email = email,
            username = username,
            password = password
        )
    }
}
