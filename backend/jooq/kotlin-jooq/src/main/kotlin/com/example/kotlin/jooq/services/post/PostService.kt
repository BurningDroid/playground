package com.example.kotlin.jooq.services.post

import com.example.generated.tables.pojos.Post
import com.example.kotlin.jooq.dto.page.PageRequest
import com.example.kotlin.jooq.dto.page.PageResponse
import com.example.kotlin.jooq.dto.post.PostCreateServiceDto
import com.example.kotlin.jooq.dto.post.PostResponse
import com.example.kotlin.jooq.dto.post.PostWithUserResponse
import com.example.kotlin.jooq.dto.post.toResponse
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

    fun findById(id: Long): PostResponse {
        val post: Post = postRepo.findById(id)
        return post.toResponse()
    }

    fun findAll(pageRequest: PageRequest, query: String): PageResponse<PostWithUserResponse> {
        return postRepo.findAll(pageRequest, query)
    }
}