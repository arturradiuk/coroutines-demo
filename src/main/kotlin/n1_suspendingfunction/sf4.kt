package com.aradiuk.suspendingfunction

import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    println("Before suspendCoroutine call")
    suspendCoroutine<Unit> { continuation ->
        println("Right before suspendCoroutine call")
        continuation.resumeWithException(IllegalStateException())
        println("Right after resuming call")
    }
    println("After suspendCoroutine call") // will never be printed
}
//Before suspendCoroutine call
//Right before suspendCoroutine call
//Right after resuming call
//Exception in thread "main" java.lang.IllegalStateException
