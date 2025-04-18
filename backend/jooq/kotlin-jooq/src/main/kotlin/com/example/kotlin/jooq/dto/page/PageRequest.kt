package com.example.kotlin.jooq.dto.page

import jakarta.validation.constraints.Min

data class PageRequest(
    val page: Int = 1,
    val size: Int = 10,
) {
    init {
        require(page > 0) { "페이지는 반드시 > 0" }
        require(size > 0) { "사이즈는 반드시 > 0" }
    }

    fun offset(): Int = (page - 1) * size
}