import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

        delay(1000)
        println("something")
    }.invokeOnCompletion { e ->
        println(e)
    }


    delay(300)

    launch {
        println(delay(500))
        println("fourth")
    }
}