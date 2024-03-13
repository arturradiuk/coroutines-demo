package com.aradiuk.builders

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

fun main() {
    println("Before first: ${Thread.currentThread().name}")
    GlobalScope.launch {
        delay(1000L)
        println("First coroutine: ${Thread.currentThread().name}")
    }
    println("Before second: ${Thread.currentThread().name}")
    GlobalScope.launch {
        delay(1000L)
        println("Second coroutine: ${Thread.currentThread().name}")
    }
    println("The end: ${Thread.currentThread().name}")
    Thread.sleep(1500L)
}
