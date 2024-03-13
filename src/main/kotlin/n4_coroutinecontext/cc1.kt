import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println(coroutineContext.fold("") { acc, element ->
            "${acc} ${element::class.simpleName} - ${element} \n"
        })
    }
}