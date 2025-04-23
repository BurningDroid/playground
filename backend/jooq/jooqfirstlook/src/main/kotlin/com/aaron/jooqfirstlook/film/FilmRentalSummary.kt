package com.aaron.jooqfirstlook.film

import java.math.BigDecimal

data class FilmRentalSummary(
    val filmId: Long,
    val title: String,
    val averageRentalDuration: BigDecimal,
)