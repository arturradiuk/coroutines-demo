import kotlinx.coroutines.*

fun main() {
    runBlocking {
        exceptionHandling()
//        cancellation()
        launch {
            delay(500)
            println("Last coroutine")
        }
    }
}

private suspend fun cancellation() {
    coroutineScope {
        launch {
            delay(150)
            println("first")
        }
        launch {
            delay(250)
            throw CancellationException()
        }
        launch {
            delay(250)
            println("third")
        }
    }
}

private suspend fun exceptionHandling() {
    coroutineScope {
        launch {
            delay(150)
            println("first")
        }
        launch {
            delay(250)
            throw Error()
        }
        launch {
            delay(300)
            println("third")
        }
    }
}