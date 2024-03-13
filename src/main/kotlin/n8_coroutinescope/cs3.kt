import kotlinx.coroutines.*

fun main() {
    runBlocking {
        someFun()
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