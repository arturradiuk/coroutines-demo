import kotlinx.coroutines.*

suspend fun main(): Unit = runBlocking {
    launch {
        launch {
            delay(200)
            println("first coroutine")
        }
        launch {
            delay(250)
            coroutineContext[Job]?.parent?.cancel()
            println("third coroutine")
        }

        try {
            delay(1000)
        } catch (e: CancellationException) {
            println(e)
//            throw e
        }finally {
            println("clean up")
        }
        println("something")
    }
}