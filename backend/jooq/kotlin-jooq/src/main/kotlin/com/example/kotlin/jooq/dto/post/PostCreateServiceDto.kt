package com.example.kotlin.jooq.dto.post

import com.example.generated.tables.pojos.Post

data class PostCreateServiceDto(
    private val title: String,
    private val content: String,
    private val userId: Long
) {
    fun toEntity(): Post {
        return Post(
            title = title,
            content = content,
            userId = userId
        )
    }
}
