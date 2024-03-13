package com.aradiuk.builders

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

fun main() {
    runThreads()
    runCoroutines()
    Thread.sleep(1000)
}

fun runCoroutines() {
    GlobalScope.launch {
        println("Before launching first coroutine")
        delay(100)
        println("After launching first coroutine")
    }
    GlobalScope.launch {
        println("Before launching second coroutine")
        delay(100)
        println("After launching second coroutine")
    }
}

fun runThreads() {
    thread(isDaemon = true) {
        Thread.sleep(100)
        println("After launching first thread")
    }
    thread(isDaemon = true) {
        Thread.sleep(100)
        println("After launching second thread")
    }
}