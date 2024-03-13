package com.aradiuk.builders

import kotlinx.coroutines.*

fun main() {
    repeat(10) { index ->
        GlobalScope.launch {
            println("$index before thread is: ${Thread.currentThread().name}")
            delay(1000L)
            println("$index after thread is: ${Thread.currentThread().name}")
        }
    }
    Thread.sleep(1500L)
}