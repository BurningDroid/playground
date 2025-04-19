package com.example.kotlin.jooq.utils.jooq.extensions

import com.example.kotlin.jooq.utils.jooq.conditions.JooqConditions
import org.jooq.Condition
import org.jooq.Field

fun Field<String?>.likeIfNotBlank(value: String?): Condition {
    return JooqConditions.likeIfNotBlank(this, value)
}