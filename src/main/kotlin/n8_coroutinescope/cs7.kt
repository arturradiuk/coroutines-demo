import kotlinx.coroutines.*

fun main() = runBlocking {
    someFun()
    println("Done")
}

private suspend fun someFun() = coroutineScope { // completes only after both are complete
    launch {
        delay(2000L)
        println("World 2")
    }
    val job = launch {
        delay(1000L)
        println("World 2")
    }
    job.parent?.cancel()
    println("Hello")
}