import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main(): Unit = runBlocking {
    val job = launch {
        repeat(10) { i ->
            delay(200)
            println("done with $i")
        }
    }
    delay(500)
    job.cancel() // cancels the job
    println("cancelled")
}