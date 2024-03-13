package com.aradiuk.builders

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Before delay")
        delay(1000)
        println("After delay")
    }
}
