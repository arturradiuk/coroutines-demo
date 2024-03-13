import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext

fun main() {
    runBlocking {
        println(coroutineContext[Job])
        launch(Job()) {
            println(coroutineContext[Job]?.parent)
            delay(1000)
            println("Will not be printed")
        }
    }
}