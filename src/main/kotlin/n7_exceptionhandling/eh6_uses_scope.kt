import kotlinx.coroutines.*

fun main() {
    runBlocking {
        exceptionHandling()
        launch {
            delay(500)
            println("Last coroutine")
        }
    }
}

private suspend fun exceptionHandling() {
    coroutineScope {
        val value = async<Int> {
            delay(250)
            throw Error()
        }

        try {
            value.await()
        } catch (e: Error) {
            println(e)
        }
    }
}