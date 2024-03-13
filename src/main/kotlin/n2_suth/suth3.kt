package com.aradiuk.n2_suth

import kotlinx.coroutines.delay

suspend fun main() {
    firstSuspendableFunction()
}

private suspend fun firstSuspendableFunction(): Int {
    var someNumber = 4
    println("First before, someNumber: ${someNumber}")
    secondSuspendableFunction()
    someNumber = 5
    println("First after, someNumber: ${someNumber}")
    return someNumber
}

private suspend fun secondSuspendableFunction(): String {
    var someString = "hello"
    println("Second before, someString: ${someString}")
    delay(1000)
    someString = "hello, world"
    println("Second after, someString: ${someString}")
    return someString
}