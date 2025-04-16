package com.example.com.example.kotlinjooq

import org.jooq.codegen.DefaultGeneratorStrategy
import org.jooq.codegen.GeneratorStrategy
import org.jooq.meta.Definition

class PrefixGeneratorStrategy: DefaultGeneratorStrategy() {
    override fun getJavaClassName(definition: Definition?, mode: GeneratorStrategy.Mode?): String {
        return when(mode) {
            GeneratorStrategy.Mode.DEFAULT -> super.getJavaClassName(definition, mode) + "_"
            else -> super.getJavaClassName(definition, mode)
        }
    }
}