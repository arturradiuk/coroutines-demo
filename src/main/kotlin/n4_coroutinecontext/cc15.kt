package com.aradiuk.n4_coroutinecontext

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val parentJob = Job()
        launch(parentJob) {
            delay(100)
            println("hello world")
        }
    }
}