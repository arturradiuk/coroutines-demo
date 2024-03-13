package com.aradiuk.suspendingfunction

import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    println("Before suspendCoroutine call")
    suspendCoroutine<Unit> {
        println("Right before suspendCoroutine call")
    }
    println("After suspendCoroutine call") // will never be printed
}
//Before suspendCoroutine call
//Right before suspendCoroutine call
