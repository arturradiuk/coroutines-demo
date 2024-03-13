package com.aradiuk.n2_suth

import kotlinx.coroutines.delay
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

private suspend fun sampleSuspendableFunction(continuation: Continuation<*>): Any? {
    println("Before delay")
    delay(1000)
    println("After delay")
    TODO()
}
