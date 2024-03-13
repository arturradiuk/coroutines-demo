import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val job = SupervisorJob()
        launch(job) { // be aware
            launch {
                delay(100)
                throw IllegalStateException()
            }

            launch {
                delay(200)
                println("Last coroutine")
            }
        }
    }
}
