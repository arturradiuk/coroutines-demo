import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val job = launch {
        repeat(20) { i ->
            delay(100)
            Thread.sleep(300)
            println("done with $i")
        }
    }
    delay(700)
    job.cancel() // cancels the job
    job.join() // waits for job's completion
    println("cancelled")
}
