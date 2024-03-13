import kotlinx.coroutines.*

suspend fun main(): Unit = runBlocking { // cor 1
    launch { // cor 2
        launch { // cor 3
            delay(200)
            println("first coroutine")
        }

        launch { // 4
            delay(400)
            println("second coroutine")
        }

        launch { // 5
            delay(250)
            println("third coroutine")
            throw CancellationException()
//            coroutineContext[Job]?.parent?.cancel()
        }
    }


    delay(300)

    launch { // 6
        println(delay(500))
        println("fourth")
    }
}