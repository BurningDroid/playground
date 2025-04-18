package com.example.kotlin.jooq.services.user

import com.example.kotlin.jooq.dto.user.UserCreateServiceDto
import com.example.kotlin.jooq.dto.user.UserResponse
import com.example.kotlin.jooq.dto.user.toUserResponse
import com.example.kotlin.jooq.repo.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepo: UserRepository,
) {
    @Transactional
    fun create1(dto: UserCreateServiceDto): Long {
        val user = dto.toEntity()
        return userRepo.create1(user)
    }

    @Transactional
    fun create2(dto: UserCreateServiceDto): Long {
        val user = dto.toEntity()
        return userRepo.create2(user)
    }

    fun findById(id: Long): UserResponse {
        return userRepo.findById(id).toUserResponse()
    }
}