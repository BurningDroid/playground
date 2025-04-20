package com.example.kotlin.jooq.record

import com.example.generated.tables.Users_
import com.example.generated.tables.records.UsersRecord
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.Result
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RecordTest {

    val log = LoggerFactory.getLogger(this::class.java)

    private companion object {
        val USERS = Users_.USERS
    }

    @Autowired
    lateinit var dslContext: DSLContext

    @DisplayName("Record를 사용해서 모든 사용자 조회하기")
    @Test
    fun findAllTest() {
        val result1: Result<Record> = dslContext.select()
            .from(USERS)
            .fetch()

        log.info(result1.toString())

        log.info("==================== Record ====================")
        result1.forEach { record ->
            log.info(record.get(USERS.USERNAME))
            log.info(record[USERS.USERNAME])
        }


        val result2: Result<UsersRecord> = dslContext.select()
            .from(USERS)
            .fetchInto(USERS)

        log.info(result2.toString())

        log.info("==================== UsersRecord ====================")
        result2.forEach { record ->
            log.info(record.get(USERS.USERNAME))
            log.info(record[USERS.USERNAME])
        }
    }

    @DisplayName("Record를 사용해서 특정 아이디로 사용자 조회하기")
    @Test
    fun findByIdTest() {
        val record1: Record = dslContext.select()
            .from(USERS)
            .where(USERS.ID.eq(1))
            .fetchOne() ?: throw RuntimeException("user not found")
        val username1 = record1.get(USERS.USERNAME)

        val record2: UsersRecord = dslContext.select()
            .from(USERS)
            .where(USERS.ID.eq(1))
            .fetchOneInto(USERS) ?: throw RuntimeException("user not found")
        val username2 = record2.get(USERS.USERNAME)
        val username3 = record2.username

        val record3: UsersRecord = dslContext.selectFrom(USERS)
            .where(USERS.ID.eq(1))
            .fetchOne() ?: throw RuntimeException("user not found")

        log.info(username1)
        log.info(username2)
        log.info(username3)
    }

    @DisplayName("Record 업데이트 후 최신 정보 가져오기")
    @Test
    fun refreshTest() {
        val record: UsersRecord = dslContext.selectFrom(USERS)
            .where(USERS.ID.eq(1))
            .fetchOne() ?: throw RuntimeException("user not found")

        dslContext.update(USERS)
            .set(USERS.USERNAME, "change_username")
            .where(USERS.ID.eq(1))
            .execute()

        record.refresh()

        log.info("==================== update ====================")
        log.info(record.username)
    }
}