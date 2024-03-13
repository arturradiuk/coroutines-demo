import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking(CoroutineName("runBlocking")) {
        println(coroutineContext.fold("runBlocking \n") { acc, element ->
            "${acc} ${element::class.simpleName} - ${element} \n"
        })
        launch {
            println(coroutineContext.fold("launch \n") { acc, element ->
                "${acc} ${element::class.simpleName} - ${element} \n"
            })
        }
    }
}