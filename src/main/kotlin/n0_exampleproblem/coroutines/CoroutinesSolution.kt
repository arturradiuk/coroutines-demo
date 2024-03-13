package com.aradiuk.exampleproblem.coroutines

import com.aradiuk.exampleproblem.*
import kotlinx.coroutines.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.math.BigDecimal
import kotlin.coroutines.resumeWithException
import kotlin.time.measureTime

fun main() {
    val order = Order(
        userId = "userId",
        cartId = "cartId",
        chosenDeliveryMethodId = "STORE",
        chosenPaymentMethod = "BLIK",
        price = BigDecimal(193.80)
    )
    measureTime {
        runBlocking {
            submitOrder(order)
        }
    }.inWholeMilliseconds.also {
        println("Lasted $it milliseconds")
    }
}

val client = OkHttpClient.Builder().build()

suspend fun submitOrder(order: Order) = coroutineScope {
    val userSubscriptions = async { fetchUserSubscriptions(userId = order.userId) }
    val availableDeliveryMethods = async { fetchDeliveryMethods(cartId = order.cartId, userSubscriptions.await()) }

    val cartOffers = async { fetchCartOffers(cartId = order.cartId) }
    val availablePaymentMethods = async { fetchPaymentMethods(cartId = order.cartId) }

    val orderData = OrderData(
        cartOffers = cartOffers.await(),
        availableDeliveryMethods = availableDeliveryMethods.await(),
        availablePaymentMethods = availablePaymentMethods.await(),
        userSubscriptions = userSubscriptions.await()
    )

    Common.validateOrderData(order = order, orderData = orderData)
    Common.placeOrder(order)
}


suspend fun fetchCartOffers(cartId: String): List<Offer> {
    println("thread is: ${Thread.currentThread().name}")
    return client.newCall(RequestUtils.prepareCartOffersRequest(cartId)).executeAsync().toOffers()
}


suspend fun fetchDeliveryMethods(cartId: String, subscriptions: List<UserSubscription>): List<DeliveryMethod> {
    return client.newCall(RequestUtils.prepareDeliveryMethodsRequest(cartId)).executeAsync().toDeliveryMethods()
}


suspend fun fetchPaymentMethods(cartId: String): List<PaymentMethod> {
    return client.newCall(RequestUtils.preparePaymentMethodsRequest(cartId)).executeAsync().toPaymentMethods()
}


suspend fun fetchUserSubscriptions(userId: String): List<UserSubscription> {
    return client.newCall(RequestUtils.prepareUserSubscriptionsRequest(userId)).executeAsync().toUserSubscriptions()
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