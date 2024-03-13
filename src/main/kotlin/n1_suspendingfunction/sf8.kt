package com.aradiuk.suspendingfunction

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    println("Before suspendCoroutine call")
    delay(1000)
    println("After suspendCoroutine call")
}

private val resumingExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor {
    Thread(it, "resumingExecutorThread").apply { isDaemon = true }
}

private fun resumeAfter(continuation: Continuation<Unit>, milliseconds: Long) {
    resumingExecutor.schedule({
        continuation.resume(Unit)
    }, milliseconds, TimeUnit.MILLISECONDS)
}

private suspend fun delay(milliseconds: Long) { // custom delay
    suspendCoroutine<Unit> { continuation ->
        resumeAfter(continuation, milliseconds)
    }
}