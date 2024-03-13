import kotlinx.coroutines.*

suspend fun main() {
    coroutineScope {
        println(coroutineContext[Job])
        val job = Job()
        launch(job) { // the new job replaces one from parent
            println(coroutineContext[Job]?.parent)
            delay(1000)
            println("Will be printed")
        }
        job.children.forEach { it.join() }
//        job.join() still in active state
    }
}