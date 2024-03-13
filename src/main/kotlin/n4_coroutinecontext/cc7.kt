import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext

fun main() {
    runBlocking {
        val job = Job()
        println(job)
        println(job.isActive)
        println(job.children.count())

        job.complete()
        println(job.isActive)
    }
}