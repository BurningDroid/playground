package com.example.kotlin.jooq.controller.user

import com.example.kotlin.jooq.dto.user.UserRequest
import com.example.kotlin.jooq.services.UserService
import jakarta.validation.Valid
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
    fun create1(@Valid @RequestBody request: UserRequest): Long {
        val user = request.toCreateServiceDto()
        return userService.create1(user)
    }

    @PostMapping("/v2")
    fun create2(@Valid @RequestBody request: UserRequest): Long {
        val user = request.toCreateServiceDto()
        return userService.create2(user)
    }
}