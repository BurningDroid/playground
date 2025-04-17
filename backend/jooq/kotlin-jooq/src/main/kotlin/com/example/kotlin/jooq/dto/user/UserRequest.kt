package com.example.kotlin.jooq.dto.user

import com.example.kotlin.jooq.annotation.Email
import jakarta.validation.constraints.NotBlank

data class UserRequest(
    private val id: Long? = null,
    @field:Email
    private val email: String,
    @field:NotBlank(message = "유저 이름은 필수입니다.")
    private val username: String,
    @field:NotBlank(message = "비밀번호는 필수입니다.")
    private val password: String,
) {
    fun toCreateServiceDto(): UserCreateServiceDto {
        return UserCreateServiceDto(
            email = email,
            username = username,
            password = password
        )
    }
}
