package com.example.kotlin.jooq.utils.jooq.conditions

import org.jooq.Condition
import org.jooq.Field
import org.jooq.impl.DSL

object JooqConditions {
    fun likeIfNotBlank(field: Field<String?>, value: String?): Condition {
        return value?.takeIf { it.isNotBlank() }
            ?.let { field.like("%$it%") }
            ?: DSL.noCondition()
    }
}