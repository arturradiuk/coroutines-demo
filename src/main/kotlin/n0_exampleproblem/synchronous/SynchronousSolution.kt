package com.aradiuk.exampleproblem.synchronous

import com.aradiuk.exampleproblem.*
import okhttp3.OkHttpClient
import java.math.BigDecimal
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

val client = OkHttpClient.Builder().build()

fun submitOrder(order: Order) {
    val userSubscriptions = fetchUserSubscriptions(userId = order.userId)
    val availableDeliveryMethods = fetchDeliveryMethods(cartId = order.cartId, userSubscriptions)

    val cartOffers = fetchCartOffers(cartId = order.cartId)
    val availablePaymentMethods = fetchPaymentMethods(cartId = order.cartId)

    val orderData = OrderData(
        cartOffers = cartOffers,
        availableDeliveryMethods = availableDeliveryMethods,
        availablePaymentMethods = availablePaymentMethods,
        userSubscriptions = userSubscriptions
    )

    Common.validateOrderData(order = order, orderData = orderData)
    Common.placeOrder(order)
}


fun fetchCartOffers(cartId: String): List<Offer> {
    return client.newCall(RequestUtils.prepareCartOffersRequest(cartId)).execute().toOffers()
}


fun fetchDeliveryMethods(cartId: String, subscriptions: List<UserSubscription>): List<DeliveryMethod> {
    return client.newCall(RequestUtils.prepareDeliveryMethodsRequest(cartId)).execute().toDeliveryMethods()
}


fun fetchPaymentMethods(cartId: String): List<PaymentMethod> {
    return client.newCall(RequestUtils.preparePaymentMethodsRequest(cartId)).execute().toPaymentMethods()
}


fun fetchUserSubscriptions(userId: String): List<UserSubscription> {
    return client.newCall(RequestUtils.prepareUserSubscriptionsRequest(userId)).execute().toUserSubscriptions()
}
