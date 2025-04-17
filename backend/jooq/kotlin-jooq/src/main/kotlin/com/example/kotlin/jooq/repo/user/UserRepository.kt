package com.example.kotlin.jooq.repo.user

import com.example.generated.tables.Users_
import com.example.generated.tables.daos.UsersDao
import com.example.generated.tables.pojos.Users
import com.example.kotlin.jooq.exceptions.NotFoundException
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class UserRepository(
    private val dslContext: DSLContext, // JooqAutoConfiguration에 의해 생성
    private val usersDao: UsersDao
) {

    companion object {
        private val USERS: Users_ = Users_()
    }

    fun create1(user: Users): Long {
        return dslContext.insertInto(
            USERS,
            USERS.EMAIL,
            USERS.USERNAME,
            USERS.PASSWORD,
        ).values(user.email, user.username, user.password)
            .returningResult(USERS.ID)
            .fetchOneInto(Long::class.java) ?: throw IllegalArgumentException()
    }

    fun create2(user: Users): Long {
        user.createdAt = LocalDateTime.now()
        user.updatedAt = LocalDateTime.now()
        usersDao.insert(user)
        return user.id ?: throw IllegalArgumentException()
    }

    fun findById(id: Long): Users {
        return dslContext.selectFrom(USERS)
            .where(USERS.ID.eq(id))
            .fetchOneInto(Users::class.java) ?: throw NotFoundException("해당 ID: ${id}를 가진 사용자를 찾을 수 없습니다.")
    }
}