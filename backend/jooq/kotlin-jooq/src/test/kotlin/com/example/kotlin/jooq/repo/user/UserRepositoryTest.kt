package com.example.kotlin.jooq.repo.user

import com.example.generated.tables.pojos.Users
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    lateinit var userRepo: UserRepository

    @Test
    fun create1Test() {
        val user = createUser()

        val users = userRepo.create1(user)

        assertThat(users).isNotNull()
        assertThat(users.createdAt).isNotNull()
        assertThat(users.updatedAt).isNotNull()
    }

    @Test
    fun create2Test() {
        val user = createUser()

        val id = userRepo.create2(user)

        assertThat(id).isEqualTo(user.id)
    }

    companion object {
        private fun createUser() = Users(
            email = "test@test.com",
            username = "Aaron",
            password = "1234"
        )
    }
}