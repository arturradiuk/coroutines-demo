import kotlinx.coroutines.*

fun main() = runBlocking {
    someFun()
    println("Done")
}

private suspend fun someFun() = coroutineScope { // completes only after both are complete
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        throw IllegalStateException()
    }
    println("Hello")
}