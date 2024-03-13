import kotlinx.coroutines.*
import kotlin.time.measureTime

fun main() {
    runBlocking {
        val job = launch {
            delay(1000)
        }

        println(coroutineContext[Job])
        println(job.parent)

        println(job)
        println(coroutineContext[Job]?.children?.first())

    }
}