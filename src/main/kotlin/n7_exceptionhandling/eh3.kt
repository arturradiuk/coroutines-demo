import kotlinx.coroutines.*

fun main() {
    runBlocking {
        try {
            sampleFun()
        }catch (e: IllegalStateException) {
            println(e)
        }
    }
}

private suspend fun sampleFun() {
    coroutineScope {
        launch {
            throw IllegalStateException()
        }
    }
}