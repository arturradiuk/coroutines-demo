package com.aradiuk.builders

import kotlinx.coroutines.*

fun main() {
    val scope = CoroutineScope(Dispatchers.IO.limitedParallelism(1))
    repeat(1000) {
        scope.launch {
            delay(1000L)
            println("Thread is: ${Thread.currentThread().name}")
        }
    }
    Thread.sleep(1500L)
}