import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val job = Job()
        launch(job) {
            delay(1000)
            println("First")
        }
        launch(job) {
            delay(1500)
            coroutineContext[Job]
            job.completeExceptionally(IllegalStateException())
        }

        launch(job) {
            delay(2000)
            println("Third")
        }

        delay(1600)
        job.complete()
        job.join()

        println()
    }
}