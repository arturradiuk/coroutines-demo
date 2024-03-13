package com.aradiuk.exampleproblem.callback

import com.aradiuk.exampleproblem.*
import okhttp3.*
import java.io.IOException
import java.math.BigDecimal
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
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
        submitOrder(order)
    }.inWholeMilliseconds.also {
        println("Lasted $it milliseconds")
    }
}

val client = OkHttpClient
    .Builder()
    .connectionPool(ConnectionPool(4, 150L, TimeUnit.MILLISECONDS))
    .build().also {
        it.dispatcher.maxRequests = 4
        it.dispatcher.maxRequestsPerHost = 4
    }

fun submitOrder(order: Order) {
    val userSubscriptionsCallback = fetchUserSubscriptions(userId = order.userId)
    val subscriptionsToDeliveryMethods = userSubscriptionsCallback.future
        .thenApply {
            it to fetchDeliveryMethods(cartId = order.cartId, it).future
        }

    val cartOffers = fetchCartOffers(cartId = order.cartId)
    val availablePaymentMethods = fetchPaymentMethods(cartId = order.cartId)

    val (userSubscriptions, deliveryMethodsFuture) = subscriptionsToDeliveryMethods.join()
    val orderData = OrderData(
        cartOffers = cartOffers.future.join(),
        availableDeliveryMethods = deliveryMethodsFuture.join(),
        availablePaymentMethods = availablePaymentMethods.future.join(),
        userSubscriptions = userSubscriptions
    )

    Common.validateOrderData(order = order, orderData = orderData)

    client.dispatcher.executorService.shutdown()
    Common.placeOrder(order)
}

fun fetchCartOffers(cartId: String): ResponseCallback<List<Offer>> {
    val responseCallback = ResponseCallback { it.toOffers() }
    client.newCall(RequestUtils.prepareCartOffersRequest(cartId))
        .enqueue(responseCallback)
    return responseCallback
}


fun fetchDeliveryMethods(
    cartId: String,
    subscriptions: List<UserSubscription>
): ResponseCallback<List<DeliveryMethod>> {
    val responseCallback = ResponseCallback { it.toDeliveryMethods() }
    client.newCall(RequestUtils.prepareDeliveryMethodsRequest(cartId))
        .enqueue(responseCallback)
    return responseCallback
}


fun fetchPaymentMethods(cartId: String): ResponseCallback<List<PaymentMethod>> {
    val responseCallback = ResponseCallback { it.toPaymentMethods() }
    client.newCall(RequestUtils.preparePaymentMethodsRequest(cartId))
        .enqueue(responseCallback)
    return responseCallback
}


fun fetchUserSubscriptions(userId: String): ResponseCallback<List<UserSubscription>> {
    val responseCallback = ResponseCallback { it.toUserSubscriptions() }
    client.newCall(RequestUtils.prepareUserSubscriptionsRequest(userId))
        .enqueue(responseCallback)
    return responseCallback
}

class ResponseCallback<T>(
    private val mapper: (Response) -> T
) : Callback {
    val future: CompletableFuture<T> = CompletableFuture()
    override fun onFailure(call: Call, e: IOException) {
        future.completeExceptionally(e)
    }

    override fun onResponse(call: Call, response: Response) {
        future.complete(mapper(response))
    }
}

