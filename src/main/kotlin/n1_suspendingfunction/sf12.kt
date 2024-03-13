package com.aradiuk.suspendingfunction

import com.aradiuk.exampleproblem.RequestUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resumeWithException
import kotlin.time.measureTime

fun main() {
    measureTime {
        runBlocking(Dispatchers.IO.limitedParallelism(1)) {
            repeat(1000) {
                launch {
                    val client = OkHttpClient.Builder().build()
                    client.newCall(RequestUtils.prepareCartOffersRequest("cartId"))
                        .executeAsync()
                    client.closeExecutor()
                }
            }
        }
    }.also {
        println("Execution lasted $it milliseconds")
    }
}

fun OkHttpClient.closeExecutor() = this.dispatcher.executorService.shutdown()

private suspend fun Call.executeAsync(): Response =
    suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            this.cancel()
        }
        this.enqueue(
            object : Callback {
                override fun onFailure(
                    call: Call,
                    e: IOException,
                ) {
                    continuation.resumeWithException(e)
                }

                override fun onResponse(
                    call: Call,
                    response: Response,
                ) {
                    continuation.resume(value = response, onCancellation = { call.cancel() })
                }
            },
        )
    }