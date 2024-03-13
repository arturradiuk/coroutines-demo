import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val coroutineName = CoroutineName("some name")
        launch(coroutineName) {
            println("context: ${coroutineContext}")
            coroutineScope {
                launch {
                    println("context: ${coroutineContext}")
                }
            }
        }
    }
}