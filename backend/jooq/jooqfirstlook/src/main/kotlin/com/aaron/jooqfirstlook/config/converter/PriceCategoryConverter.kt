package com.aaron.jooqfirstlook.config.converter

import com.aaron.jooqfirstlook.film.PriceCategory
import org.jooq.impl.EnumConverter

class PriceCategoryConverter :
    EnumConverter<String, PriceCategory>(String::class.java, PriceCategory::class.java, PriceCategory::code) {
}