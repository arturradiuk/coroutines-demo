package com.aradiuk.suspendingfunction

import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    println("Before suspendCoroutine call")
    suspendCoroutine<Unit> { continuation ->
        thread {
            Thread.sleep(1000)
            continuation.resume(Unit)
        }
    }
    println("After suspendCoroutine call")
}