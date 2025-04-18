package com.example.kotlin.jooq.dto.page

data class PageResponse<T>(
    val content: List<T>,
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int,
    val isFirst: Boolean,
    val isLast: Boolean,
) {
    companion object {
        fun <T> of(
            content: List<T>,
            page: Int,
            size: Int,
            totalElements: Long,
        ): PageResponse<T> {
            val totalPages = (totalElements / size).toInt() + if (totalElements % size > 0) 1 else 0
            return PageResponse(
                content = content,
                page = page,
                size = size,
                totalElements = totalElements,
                totalPages = totalPages,
                isFirst = page == 1,
                isLast = page >= totalPages
            )
        }
    }
}