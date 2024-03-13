package com.aradiuk.builders

import kotlinx.coroutines.*

fun main() {
    runBlocking(Dispatchers.Unconfined) {
        repeat(5) { index ->
            launch {
                println("$index before thread is: ${Thread.currentThread().name}")
                delay(1000L)
                println("$index after thread is: ${Thread.currentThread().name}")
            }
        }
    }
}