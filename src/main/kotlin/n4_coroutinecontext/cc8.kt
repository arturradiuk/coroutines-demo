import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext

fun main() {
    runBlocking {
        val job = Job()
//        val launchJob = launch(job, start = CoroutineStart.LAZY) {
        val launchJob = launch(job) {
            delay(1000)
            println("something")
        }
//        println(launchJob)
//        println(launchJob.parent)
//        launchJob.join()

        job.join()
        println(job.children.count())
    }
}