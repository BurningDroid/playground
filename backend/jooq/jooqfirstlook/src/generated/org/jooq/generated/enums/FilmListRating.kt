/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.enums


import org.jooq.Catalog
import org.jooq.EnumType
import org.jooq.Schema


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
enum class FilmListRating(@get:JvmName("literal") public val literal: String) : EnumType {
    G("G"),
    PG("PG"),
    `PG-13`("PG-13"),
    R("R"),
    `NC-17`("NC-17");
    override fun getCatalog(): Catalog? = null
    override fun getSchema(): Schema? = null
    override fun getName(): String? = null
    override fun getLiteral(): String = literal
}
