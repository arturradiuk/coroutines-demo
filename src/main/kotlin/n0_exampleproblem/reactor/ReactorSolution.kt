package com.aradiuk.exampleproblem.reactor

import com.aradiuk.exampleproblem.*
import io.netty.handler.codec.http.HttpMethod.GET
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
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


fun submitOrder(order: Order) {
    val userSubscriptionsMono = fetchUserSubscriptions(userId = order.userId)
    val userSubscriptionsTo = userSubscriptionsMono.map {
        it to fetchDeliveryMethods(cartId = order.cartId, subscriptions = it)
    }

    val cartOffers = fetchCartOffers(cartId = order.cartId)
    val availablePaymentMethods = fetchPaymentMethods(cartId = order.cartId)

    val (userSubscriptions, deliveryMethodsMono) = userSubscriptionsTo.block()!!
    val zip = Mono.zip(
        cartOffers, deliveryMethodsMono, availablePaymentMethods
    ).block()

    val orderData = OrderData(
        cartOffers = zip.t1,
        availableDeliveryMethods = zip.t2,
        availablePaymentMethods = zip.t3,
        userSubscriptions = userSubscriptions
    )

    Common.validateOrderData(order = order, orderData = orderData)

    Common.placeOrder(order)
}

fun fetchCartOffers(cartId: String): Mono<List<Offer>> {
    return HttpClient.create().request(GET).uri(RequestUtils.prepareCartOffersUrl(cartId)).responseSingle { _, buf ->
        buf.map {
            it.toOffers()
        }
    }
}


fun fetchDeliveryMethods(cartId: String, subscriptions: List<UserSubscription>): Mono<List<DeliveryMethod>> {
    return HttpClient.create().request(GET).uri(RequestUtils.prepareDeliveryMethodsUrl(cartId))
        .responseSingle { _, buf ->
            buf.map {
                it.toDeliveryMethods()
            }
        }
}


fun fetchPaymentMethods(cartId: String): Mono<List<PaymentMethod>> {
    return HttpClient.create().request(GET).uri(RequestUtils.preparePaymentMethodsUrl(cartId))
        .responseSingle { _, buf ->
            buf.map {
                it.toPaymentMethods()
            }
        }
}


fun fetchUserSubscriptions(userId: String): Mono<List<UserSubscription>> {
    return HttpClient.create().request(GET).uri(RequestUtils.prepareUserSubscriptionsUrl(userId))
        .responseSingle { _, buf ->
            buf.map {
                it.toUserSubscriptions()
            }
        }
}

