package com.example.kotlin.jooq.services.post

import com.example.generated.tables.pojos.Post
import com.example.kotlin.jooq.dto.page.PageRequest
import com.example.kotlin.jooq.dto.page.PageResponse
import com.example.kotlin.jooq.dto.post.*
import com.example.kotlin.jooq.repo.post.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepo: PostRepository,
) {
    @Transactional
    fun create(dto: PostCreateServiceDto): Long {
        val post = dto.toEntity()
        return postRepo.create(post)
    }

    fun findById(id: Long): PostResponse = postRepo.findById(id).toResponse()

    fun findById2(id: Long): PostWithUserResponse = postRepo.findById2(id)

    fun findById3(id: Long): PostWithUserResponse = postRepo.findById3(id)

    fun findAll(pageRequest: PageRequest, query: String): PageResponse<PostWithUserResponse> {
        return postRepo.findAll(pageRequest, query)
    }

    fun findAll2(pageRequest: PageRequest, query: String): PageResponse<PostWithUserResponse> {
        return postRepo.findAll2(pageRequest, query)
    }

    fun findAll3(pageRequest: PageRequest, query: String): PageResponse<PostWithUserResponse> {
        return postRepo.findAll3(pageRequest, query)
    }

    @Transactional
    fun update(serviceDto: PostUpdateServiceDto): Long {
        val post = serviceDto.toEntity()
        return postRepo.update(post)
    }

    @Transactional
    fun delete(id: Long): Int {
        return postRepo.delete(id)
    }
}