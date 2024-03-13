import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

fun main() {
    runBlocking(CoroutineName("hi")) {
        mySuspendFun()
    }
}

private suspend fun mySuspendFun() {
    println(coroutineContext[CoroutineName])
}