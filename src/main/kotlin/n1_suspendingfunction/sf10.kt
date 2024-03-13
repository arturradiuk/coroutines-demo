package com.aradiuk.suspendingfunction

import com.aradiuk.exampleproblem.RequestUtils
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

suspend fun main() {
    val client = OkHttpClient.Builder().build()
    val call = client.newCall(RequestUtils.prepareCartOffersRequest("cartId"))
//    call.execute()
    call.enqueue(
        object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                println(response.body?.string())
            }
        }
    )
    client.dispatcher.executorService.shutdown()
}
