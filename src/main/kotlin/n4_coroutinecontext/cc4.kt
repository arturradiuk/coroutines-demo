import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    val coroutineName: CoroutineName = CoroutineName("some name")
    val coroutineContext: CoroutineContext = coroutineName

    println(coroutineContext[CoroutineName])
    println(coroutineContext + CoroutineName("new name") + Job())

    val emptyContext = EmptyCoroutineContext
    println(emptyContext)
}