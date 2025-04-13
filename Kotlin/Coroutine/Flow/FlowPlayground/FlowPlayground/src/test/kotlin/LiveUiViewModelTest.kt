
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.example.flow.LiveUiViewModel
import org.example.flow.SetupLive
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class LiveUiViewModelTest {
    @RelaxedMockK
    private lateinit var setupLive: SetupLive
    private lateinit var testDispatcher: CoroutineDispatcher
    private lateinit var vm: LiveUiViewModel

    @BeforeEach
    fun setup() = runTest {
        /**
         * 테스트에서 사용될 Dispatcher
         *
         * testScheduler: 테스트 수행 동안 TestDispatcher가 테스트를 수행하면서 가상 시간을 다루기 위해 사용하는 TestCoroutineScheduler
         */
        testDispatcher = UnconfinedTestDispatcher(testScheduler)
        vm = LiveUiViewModel(setupLive, testDispatcher)
    }

    @Nested
    inner class StartLiveTest {

        /**
         * assertion을 위해 flow 결과물을 담아놓는 list
         */
        private val toastList: MutableList<String> = mutableListOf()

        @BeforeEach
        fun setup() = runTest {
            toastList.clear()
            vm.toastFlow
                .onEach(toastList::add)
                .launchIn(CoroutineScope(testDispatcher))
        }

        @DisplayName("""
        [GIVEN: 정상 케이스]
        [WHEN: 라이브 방송 시작]
        [THEN: 성공 토스트 메시지 띄운다]""")
        @Test
        fun `startLiveTest 성공 케이스`() = runTest {
            coEvery { setupLive.setup() } returns true

            vm.startLive()

            val actual = toastList.first()
            assertEquals("라이브가 시작되었어요!", actual)
        }

        @DisplayName("""
        [GIVEN: 에러 케이스]
        [WHEN: 라이브 방송 시작]
        [THEN: 실패 토스트 메시지 띄운다]""")
        @Test
        fun `startLiveTest 에러 케이스`() = runTest {
            coEvery { setupLive.setup() } returns false

            vm.startLive()

            val actual = toastList.first()
            assertEquals("에러가 발생했어요!", actual)
        }
    }
}