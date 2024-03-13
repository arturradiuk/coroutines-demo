import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext

fun main() {
    runBlocking {
        val job: Deferred<Int> = async {
            delay(100)
            45
        }

        job.await()
    }
}