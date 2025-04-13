import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Test1Coroutine {

    @Test
    fun test1() = runTest {
        var x = 0

        launch {
            x++
        }
        launch {
            x++
        }

        assertEquals(2, x)
    }

    @Test
    fun test2() = runTest {
        var x = 0

        launch {
            delay(100)
            x++
        }
        launch {
            delay(1_000)
            x++
        }

        advanceTimeBy(200)
        assertEquals(1, x)

        advanceTimeBy(1_500)
        assertEquals(2, x)
    }
}