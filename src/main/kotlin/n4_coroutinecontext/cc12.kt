import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val job = Job()
        launch(job) {
            delay(1000)
            println("First")
        }
        launch(job) {
            delay(2000)
            println("Second")
        }
        job.complete()
        job.join()
        println(job.isActive)
    }
}