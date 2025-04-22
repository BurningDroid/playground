package com.aaron.jooqfirstlook.utils.jooq

import org.jooq.Condition
import org.jooq.Field
import org.jooq.impl.DSL

fun <T> inIfNotEmpty(field: Field<T?>, ids: List<T>?): Condition {
    if (ids.isNullOrEmpty()) {
        return DSL.noCondition()
    }

    return field.`in`(ids)
}