import kotlinx.coroutines.*

fun main() {
    runBlocking(CoroutineName("hi")) {
        mySuspendFun()
    }
}

private suspend fun mySuspendFun() {
    withContext(CoroutineName("Hello")) {
        println(kotlin.coroutines.coroutineContext[CoroutineName])
    }
}