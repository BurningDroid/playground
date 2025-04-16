package com.example.kotlin.jooq.repo.user

import com.example.generated.tables.Users_
import com.example.generated.tables.daos.UsersDao
import com.example.generated.tables.pojos.Users
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val dslContext: DSLContext, // JooqAutoConfiguration에 의해 생성
    private val usersDao: UsersDao
) {

    companion object {
        private val USERS: Users_ = Users_()
    }

    fun create1(user: Users): Users {
        return dslContext.insertInto(
            USERS,
            USERS.EMAIL,
            USERS.USERNAME,
            USERS.PASSWORD,
        ).values(user.email, user.username, user.password)
            .returningResult(USERS)
            .fetchOneInto(Users::class.java) ?: throw IllegalArgumentException()
    }

    fun create2(user: Users): Long {
        usersDao.insert(user)
        return user.id ?: throw IllegalArgumentException()
    }
}