import kotlinx.coroutines.*

fun main() {
    runBlocking {
        launch {
            delay(100)
            throw IllegalStateException()
        }
        launch {
            delay(200)
            println("second")
        }
    }
}
