import kotlinx.coroutines.*

fun main() {
    runBlocking {
         withContext(Dispatchers.IO.limitedParallelism(1)) { // will be return only when all code inside will be executed
            launch {
                delay(200)
                println("first")
            }
             launch {
                 delay(250)
                 throw IllegalStateException()
             }
             launch {
                 delay(400)
                 println("third")
             }
        }

        println("the end")
    }
}