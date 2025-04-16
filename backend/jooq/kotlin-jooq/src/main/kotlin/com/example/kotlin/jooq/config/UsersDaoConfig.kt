package com.example.kotlin.jooq.config

import com.example.generated.tables.daos.UsersDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UsersDaoConfig {
    @Bean
    fun usersDao(configuration: org.jooq.Configuration): UsersDao = UsersDao(configuration)
}