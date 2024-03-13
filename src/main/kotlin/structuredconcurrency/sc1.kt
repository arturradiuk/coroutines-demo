import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        GlobalScope.launch {
            delay(100L)
            println("First launch coroutine")
        }
        GlobalScope.launch {
            delay(100L)
            println("Second launch coroutine")
        }
        delay(150)
    }
}