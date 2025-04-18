package com.example.kotlin.jooq.controller.user

import com.example.kotlin.jooq.api.ApiResponse
import com.example.kotlin.jooq.dto.user.UserCreateServiceDto
import com.example.kotlin.jooq.dto.user.UserRequest
import com.example.kotlin.jooq.dto.user.UserResponse
import com.example.kotlin.jooq.services.user.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<ApiResponse<UserResponse>> {
        return ResponseEntity.ok(ApiResponse.success(userService.findById(id)))
    }
}