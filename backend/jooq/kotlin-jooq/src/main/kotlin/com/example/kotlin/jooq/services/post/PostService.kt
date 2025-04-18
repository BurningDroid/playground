package com.example.kotlin.jooq.services.post

import com.example.kotlin.jooq.dto.post.PostCreateServiceDto
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
}