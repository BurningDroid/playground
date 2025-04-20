package com.example.kotlin.jooq.record

import com.example.generated.tables.Users_
import com.example.generated.tables.pojos.Users
import org.jooq.DSLContext
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ResultTest {
    val log = LoggerFactory.getLogger(this::class.java)

    private companion object {
        val USERS = Users_.USERS
    }

    @Autowired
    lateinit var dslContext: DSLContext

    @DisplayName("Result 기본 사용")
    @Test
    fun resultTest1() {
        val result = dslContext.selectFrom(USERS).fetch()

        // 1. 크기 확인
        log.info("total record count: ${result.size}")

        // 2. 순회
        result.forEach { record ->
            log.info(record.toString())
        }

        // 3. Result -> List
        val userList: List<Users> = result.into(Users::class.java)
        log.info("size: ${userList.size}")
        userList.forEach { user ->
            log.info(user.toString())
        }

        // 4. Result -> Map
        val userMap: Map<Long?, Users> = result.intoMap(USERS.ID, Users::class.java)
        log.info(userMap.toString())

        // 5. Result filter
        val filteredResult = result.filter { record -> record.username!!.contains("user") }
        log.info(filteredResult.toString())

        // 6. Result group
        val intoGroups = result.intoGroups(USERS.PASSWORD)
        intoGroups.forEach { group ->
            log.info(group.toString())
        }

        // 7. 특정 컬럼만 추출
        val usernames = result.getValues(USERS.USERNAME)
        log.info(usernames.toString())

        // 8. 첫 번째/마지막 레코드 접근
        val firstRecord = result.first()
        val lastRecord = result.last()
        log.info("first: $firstRecord")
        log.info("last: $lastRecord")

    }
}