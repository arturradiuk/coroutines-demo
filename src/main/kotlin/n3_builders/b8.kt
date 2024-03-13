package com.aradiuk.builders

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.time.measureTime

fun main() {
    val newFixedThreadPool = Executors.newFixedThreadPool(100)
    val dispatcher = newFixedThreadPool.asCoroutineDispatcher()
    measureTime {
        runBlocking(dispatcher) {
            repeat(100) { index ->
                launch {
                    Thread.sleep(1000)
//                    delay(1000)
                }
            }
        }
    }.inWholeMilliseconds.also {
        println("Execution lasted: $it")
    }


    newFixedThreadPool.shutdownNow()
}