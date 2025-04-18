package com.example.kotlin.jooq.dto.post

import jakarta.validation.constraints.NotBlank

data class PostRequest(
    val id: Long? = null,
    @field:NotBlank(message = "제목은 필수 값입니다")
    val title: String,
    @field:NotBlank(message = "내용은 필수 값입니다")
    val content: String,
    @field:NotBlank(message = "작성자 id는 필수 값입니다")
    val userId: Long,
) {
    fun toCreateServiceDto(): PostCreateServiceDto {
        return PostCreateServiceDto(
            title = title,
            content = content,
            userId = userId
        )
    }
}