import kotlinx.coroutines.*

suspend fun main(): Unit = runBlocking {

    launch {
        launch {
            delay(200)
            println("first coroutine")
        }

        launch {
            try {
                delay(1000)
            } catch (e: CancellationException) {
                println(e)
            delay(400)
            }
            println("second coroutine")
        }

        launch {
            try {
                delay(250)
            } catch (e: CancellationException) {
                println(e)
            }
            coroutineContext[Job]?.parent?.cancel()
            println("third coroutine")
        }

    }




    delay(300)

    launch {
        println(delay(500))
        println("fourth")
    }
}