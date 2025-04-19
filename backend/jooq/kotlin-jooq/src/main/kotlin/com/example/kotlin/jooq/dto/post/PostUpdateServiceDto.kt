package com.example.kotlin.jooq.dto.post

import com.example.generated.tables.pojos.Post
import com.example.generated.tables.pojos.Users

data class PostUpdateServiceDto(
    private val id: Long,
    private val title: String,
    private val content: String,
    private val userId: Long
) {
    fun toEntity(): Post {
        return Post(
            id = id,
            title = title,
            content = content,
            userId = userId
        )
    }
}
