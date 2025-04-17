package com.example.kotlin.jooq.controller.user

import com.example.kotlin.jooq.api.ApiResponse
import com.example.kotlin.jooq.dto.user.UserCreateServiceDto
import com.example.kotlin.jooq.dto.user.UserRequest
import com.example.kotlin.jooq.services.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/v1")
    fun create1(@Valid @RequestBody request: UserRequest): ResponseEntity<ApiResponse<Long>> {
        val serviceDto: UserCreateServiceDto = request.toCreateServiceDto()
        val data: Long = userService.create1(serviceDto)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(data))
    }

    @PostMapping("/v2")
    fun create2(@Valid @RequestBody request: UserRequest): Long {
        val serviceDto = request.toCreateServiceDto()
        return userService.create2(serviceDto)
    }
}