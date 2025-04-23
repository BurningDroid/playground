package com.aaron.jooqfirstlook.film

import java.math.BigDecimal

data class FilmPriceSummary(
    val filmId: Long,
    val title: String,
    val rentalRate: BigDecimal,
    val priceCategory: PriceCategory,
    val totalInventory: Long
)

enum class PriceCategory(val code: String) {
    CHEAP("Cheap"),
    MODERATE("Moderate"),
    EXPENSIVE("Expensive");

    companion object {
        fun findByCode(code: String): PriceCategory? {
            return entries.find { it.code == code }
        }
    }
}