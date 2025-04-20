package com.example.kotlin.jooq.record

import com.example.generated.tables.Users_
import com.example.generated.tables.pojos.Users
import com.example.generated.tables.records.UsersRecord
import org.jooq.*
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

    @DisplayName("Record store test")
    @Test
    fun storeTest() {
        val newRecord = dslContext.newRecord(USERS)
        log.info("before: $newRecord")

        newRecord.email = "record_test@test.com"
        newRecord.username = "record_test1"
        newRecord.password = "record1234"
        newRecord.store()
        newRecord.refresh()

        log.info("after: $newRecord")

        dslContext.newResult(USERS)[1] = newRecord
    }

    @DisplayName("조회한 Record 필드 값 변경 후 store test")
    @Test
    fun storeTest2() {
        val oldRecord = dslContext.selectFrom(USERS)
            .where(USERS.ID.eq(1))
            .fetchOne() ?: throw RuntimeException("user not found")

        log.info("old record: $oldRecord")

        oldRecord.username = "record_test1"

        oldRecord.store()
        oldRecord.refresh()

        log.info("new record: $oldRecord")
    }

    @DisplayName("Record into test")
    @Test
    fun recordIntoTest() {
        // 1. Record -> POJO
        val record: UsersRecord = dslContext.selectFrom(USERS)
            .where(USERS.ID.eq(1))
            .fetchOne() ?: throw RuntimeException("user not found")

        val users: Users = record.into(Users::class.java)
        log.info("users pojo: $users")


        // 2. Result<Record> -> List<Users>
        val records: Result<UsersRecord> = dslContext.selectFrom(USERS).fetch()

        val usersList: List<Users> = records.into(Users::class.java)
        log.info("usersList pojo: $usersList")


        // 3. 특정 필드만 Record 변환
        val partialRecord: Record3<Long?, String?, String?> = dslContext
            .select(USERS.ID, USERS.EMAIL, USERS.USERNAME)
            .from(USERS)
            .where(USERS.ID.eq(1))
            .fetchOne() ?: throw RuntimeException("user not found")

        val partialUsers= partialRecord.into(Users::class.java)
        log.info("partialUsers: $partialUsers")


        // 4. Map
        val map: Map<String, Any> = record.intoMap()
        log.info("map: $map")
    }
}