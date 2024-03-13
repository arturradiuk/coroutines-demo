import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

fun main() {
    runBlocking(CoroutineName("runBlocking")) {
        println(coroutineContext.fold("runBlocking \n") { acc, element ->
            "${acc} ${element::class.simpleName} - ${element} \n"
        })

        launch(CoroutineName("launch")) {
            println(coroutineContext.fold("launch \n") { acc, element ->
                "${acc} ${element::class.simpleName} - ${element} \n"
            })
        }
    }
}