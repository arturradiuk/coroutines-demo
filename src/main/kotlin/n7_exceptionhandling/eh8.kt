import kotlinx.coroutines.*

fun main() {
    runBlocking {
//        val job = SupervisorJob()
        val someValue = async<Int> {
            delay(250)
            throw IllegalStateException()
        }

        launch {
            delay(300)
            println("Last coroutine")
        }
        try {
            someValue.await()
        } catch (e: Throwable) {
            println(e)
        }
    }
}
