package com.example.kotlin.jooq.repo.user

import com.example.generated.tables.pojos.Post
import com.example.generated.tables.pojos.Users
import com.example.kotlin.jooq.dto.page.PageRequest
import com.example.kotlin.jooq.repo.post.PostRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class PostRepositoryTest {

    @Autowired
    lateinit var postRepo: PostRepository

    @Autowired
    lateinit var userRepo: UserRepository

    @DisplayName("게시물 조회: 검색")
    @Test
    fun findAllWithSearch() {
        val user = createUser()
        userRepo.create1(user)

        val post = createPost()
        postRepo.create(post)

        val query = "c"

        val response = postRepo.findAll(PageRequest(), query)

        assertThat(response).isNotNull()
        assertThat(response.content).hasSize(1)
    }

    @DisplayName("게시물 수정 테스트")
    @Test
    fun update() {
        val user = createUser()
        val userId = userRepo.create1(user)

        val post = createPost()
        val postId = postRepo.create(post)
        val updatedPost = Post(
            id = postId,
            title = "제목 수정",
            content = "내용 수정",
            userId = userId
        )

        val updatedPostId = postRepo.update(updatedPost)

        assertThat(updatedPostId).isEqualTo(postId)
    }

    private companion object {
        fun createPost() = Post(
            title = "제목1",
            content = "content",
            userId = 1
        )

        fun createUser() = Users(
            id = 1,
            email = "test@test.com",
            username = "Aaron",
            password = "1234"
        )
    }
}