import kotlinx.coroutines.*

fun main() {
    runBlocking {
        try {
            launch {
                delay(100)
                throw IllegalStateException()
            }
        } catch (e: Error) {
            println(e)
        }

        launch {
            delay(200)
            println("Last coroutine")
        }
    }
}
