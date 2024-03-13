import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking(Dispatchers.IO.limitedParallelism(2)) {
        println("Before FIRST launch. Thread - ${Thread.currentThread()}")
        launch {
            println("Inside FIRST launch, right BEFORE delay. Thread - ${Thread.currentThread()}")
            delay(1000)
            println("Inside FIRST launch, right AFTER delay. Thread - ${Thread.currentThread()}")
        }
        println("Before SECOND launch")
        launch {
            println("Inside SECOND launch, right BEFORE delay. Thread - ${Thread.currentThread()}")
            delay(1000)
            println("Inside SECOND launch, right AFTER delay. Thread - ${Thread.currentThread()}")
        }
        println("The end. Thread - ${Thread.currentThread()}")
    }
}