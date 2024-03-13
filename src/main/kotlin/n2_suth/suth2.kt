package com.aradiuk.n2_suth

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

private class MySampleSuspendableFunctionContinuation(val parentContinuation: Continuation<Unit>) : Continuation<Unit> {
    var label = 0
    var result: Result<Any>? = null
    override val context: CoroutineContext
        get() = parentContinuation.context

    override fun resumeWith(result: Result<Unit>) {
        this.result = result
        val res = try {
            val r = sampleSuspendableFunction(this)
            if (r == COROUTINE_SUSPENDED) return
            Result.success(r as Unit)
        } catch (e: Throwable) {
            Result.failure(e)
        }
        parentContinuation.resumeWith(res)
        TODO("Not yet implemented")
    }
}

private fun sampleSuspendableFunction(continuation: Continuation<Unit>): Any {
    val localContinuation: MySampleSuspendableFunctionContinuation
    if (continuation is MySampleSuspendableFunctionContinuation) {
        localContinuation = continuation as MySampleSuspendableFunctionContinuation // already wrapped, second call
    } else {
        localContinuation = MySampleSuspendableFunctionContinuation(continuation) // need to be wrapped, first call
    }
    if (localContinuation.label == 0) {

        println("Before delay")

        localContinuation.label = 1
        if (delay(1000, continuation) == COROUTINE_SUSPENDED) {
            return COROUTINE_SUSPENDED
        }
    }
    if (localContinuation.label == 1) {

        println("After delay")

        return Unit
    }
    throw IllegalStateException("Unreachable point has be reached.")
}


const val COROUTINE_SUSPENDED = "COROUTINE_SUSPENDED"
private fun delay(long: Long, continuation: Continuation<*>) = "COROUTINE_SUSPENDED"