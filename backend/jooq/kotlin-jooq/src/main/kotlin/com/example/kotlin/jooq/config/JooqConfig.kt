package com.example.kotlin.jooq.config

import org.jooq.conf.ExecuteWithoutWhere
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JooqConfig {
    @Bean
    fun defaultConfigurationCustomizer(): DefaultConfigurationCustomizer {
        return DefaultConfigurationCustomizer { customizer ->
            customizer.settings()
                .withExecuteUpdateWithoutWhere(ExecuteWithoutWhere.THROW)
                .withExecuteDeleteWithoutWhere(ExecuteWithoutWhere.THROW)
                .withRenderSchema(false)
        }
    }
}