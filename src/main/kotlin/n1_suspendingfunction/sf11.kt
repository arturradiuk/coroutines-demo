package com.aradiuk.suspendingfunction

import com.aradiuk.exampleproblem.RequestUtils
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resumeWithException

suspend fun main() {
    val client = OkHttpClient.Builder().build()
    val call = client.newCall(RequestUtils.prepareCartOffersRequest("cartId"))
    val executeAsync = call.executeAsync()
    println(executeAsync.body?.string())

    client.dispatcher.executorService.shutdown()
}

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