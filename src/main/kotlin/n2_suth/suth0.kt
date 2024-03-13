package com.aradiuk.n2_suth

import kotlinx.coroutines.delay

suspend fun main() {
    sampleSuspendableFunction()
}


private suspend fun sampleSuspendableFunction() {
    println("Before delay")
    delay(1000)
    println("After delay")
}