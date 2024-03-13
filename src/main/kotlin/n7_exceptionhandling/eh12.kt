import kotlinx.coroutines.*

fun main() {
    runBlocking {
        try {
            coroutineScope {
                launch {
                    delay(500)
                    throw IllegalStateException()
                }
            }
        } catch (e: Throwable){
            println("Here")
        }
    }
}
