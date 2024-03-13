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

        delay(1100)
        job.cancelAndJoin() // change my state to cancel and join
    }
}