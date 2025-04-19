package com.example.kotlin.jooq.controller.post

import com.example.kotlin.jooq.api.ApiResponse
import com.example.kotlin.jooq.dto.page.PageRequest
import com.example.kotlin.jooq.dto.page.PageResponse
import com.example.kotlin.jooq.dto.post.PostRequest
import com.example.kotlin.jooq.dto.post.PostResponse
import com.example.kotlin.jooq.dto.post.PostWithUserResponse
import com.example.kotlin.jooq.services.post.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/posts")
@RestController
class PostController(
    private val postService: PostService,
) {

    @PostMapping
    fun create(@RequestBody request: PostRequest): ResponseEntity<ApiResponse<Long>> {
        val serviceDto = request.toCreateServiceDto()
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponse.success(postService.create(serviceDto)))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) : ResponseEntity<ApiResponse<PostResponse>> {
        return ResponseEntity
            .ok(ApiResponse.success(postService.findById(id)))
    }

    @GetMapping("/v2/{id}")
    fun findById2(@PathVariable id: Long): ResponseEntity<ApiResponse<PostWithUserResponse>> {
        return ResponseEntity
            .ok(ApiResponse.success(postService.findById2(id)))
    }

    @GetMapping("/v3/{id}")
    fun findById3(@PathVariable id: Long): ResponseEntity<ApiResponse<PostWithUserResponse>> {
        return ResponseEntity
            .ok(ApiResponse.success(postService.findById3(id)))
    }

    @GetMapping("/list")
    fun findAll(
        @ModelAttribute pageRequest: PageRequest,
        @RequestParam("q", defaultValue = "") query: String,
    ): ResponseEntity<ApiResponse<PageResponse<PostWithUserResponse>>> {
        return ResponseEntity.ok(ApiResponse.success(postService.findAll(pageRequest, query)))
    }

    @GetMapping("/v2/list")
    fun findAll2(
        @ModelAttribute pageRequest: PageRequest,
        @RequestParam("q", defaultValue = "") query: String,
    ): ResponseEntity<ApiResponse<PageResponse<PostWithUserResponse>>> {
        return ResponseEntity.ok(ApiResponse.success(postService.findAll2(pageRequest, query)))
    }

    @GetMapping("/v3/list")
    fun findAll3(
        @ModelAttribute pageRequest: PageRequest,
        @RequestParam("q", defaultValue = "") query: String,
    ): ResponseEntity<ApiResponse<PageResponse<PostWithUserResponse>>> {
        return ResponseEntity.ok(ApiResponse.success(postService.findAll3(pageRequest, query)))
    }

    @PutMapping
    fun update(@RequestBody request: PostRequest): ResponseEntity<ApiResponse<Long>> {
        val serviceDto = request.toUpdateServiceDto()
        return ResponseEntity.ok(ApiResponse.success(postService.update(serviceDto)))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<ApiResponse<Int>> {
        return ResponseEntity.ok(ApiResponse.success(postService.delete(id)))
    }

}