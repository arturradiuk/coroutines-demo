package com.aradiuk.suspendingfunction

import kotlinx.coroutines.delay
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    println("Before suspendCoroutine call")
    suspendCoroutine<Unit> { continuation ->
        resumeAfter(continuation, 1000)
    }
    println("After suspendCoroutine call")
}

// normal NOT suspending function
private fun resumeAfter(continuation: Continuation<Unit>, milliseconds: Long) {
    thread {
        Thread.sleep(milliseconds)
        continuation.resume(Unit)
    }
}