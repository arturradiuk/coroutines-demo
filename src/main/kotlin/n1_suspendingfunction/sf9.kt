package com.aradiuk.suspendingfunction

import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    val int: Int = suspendCoroutine<Int> { continuation ->
        continuation.resume(-1)
    }

    val string: String = suspendCoroutine<String> { continuation ->
        continuation.resume("sample string")
    }
}
