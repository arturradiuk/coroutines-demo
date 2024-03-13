import kotlinx.coroutines.*

suspend fun main(): Unit = runBlocking(Dispatchers.IO.limitedParallelism(2)) {
    val job = launch {
        repeat(20) { i ->
            delay(100)
            Thread.sleep(300)
            println("done with $i")
        }
    }
    delay(700)
    job.cancel()
    job.join() // force coroutine to wait till last children is executed

    job.cancelAndJoin()
    println("cancelled")
}