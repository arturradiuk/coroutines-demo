package com.aradiuk.builders

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    repeat(1000) {
        GlobalScope.launch {
            delay(1000L)
            println("Thread is: ${Thread.currentThread().name}")
        }
    }
    Thread.sleep(1500L)
}