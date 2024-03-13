package com.aradiuk.suspendingfunction

import kotlin.coroutines.suspendCoroutine

suspend fun main() { // will be executed inside coroutine
    println("Before suspendCoroutine call")
    suspendCoroutine<Unit> {}
    println("After suspendCoroutine call") // will never be printed
}
// Before suspendCoroutine call