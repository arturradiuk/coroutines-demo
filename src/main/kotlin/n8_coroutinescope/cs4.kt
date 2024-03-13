import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val job = launch {
            someFun()
        }
        delay(1100)
        job.cancel()
    }
}

private suspend fun someFun() {
    coroutineScope {
        launch {
            delay(1000)
            println("first")
        }
        launch {
            delay(1500)
            println("second")
        }
    }
}