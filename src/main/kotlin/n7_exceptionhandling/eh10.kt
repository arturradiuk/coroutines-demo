import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val job = SupervisorJob()
        launch(job) {
            delay(100)
            throw IllegalStateException()
        }
        launch(job) {
            delay(200)
            println("second")
        }
        job.join()
    }
}
