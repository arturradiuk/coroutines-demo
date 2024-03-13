package com.aradiuk.builders

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    println("Before first launch")
    launch {
        callA()
    }
    println("Before second launch")
    launch {
        callB()
    }
    println("The end")
}

private suspend fun callA() {
    println("A - before delay on thread ${Thread.currentThread().name}")
    delay(1000)
    println("A - after delay on thread ${Thread.currentThread().name}")
}

private suspend fun callB() {
    println("B - before delay on thread ${Thread.currentThread().name}")
    delay(1000)
    println("B - after delay on thread ${Thread.currentThread().name}")
}
