package com.aradiuk.exampleproblem

import okhttp3.Request

object RequestUtils {
    fun prepareCartOffersRequest(cartId: String): Request {
        return Request.Builder()
            .url(prepareCartOffersUrl(cartId))
            .build()
    }

    fun prepareDeliveryMethodsRequest(cartId: String): Request {
        return Request.Builder()
            .url(prepareDeliveryMethodsUrl(cartId))
            .build()
    }

    fun preparePaymentMethodsRequest(cartId: String): Request {
        return Request.Builder()
            .url(preparePaymentMethodsUrl(cartId))
            .build()
    }

    fun prepareUserSubscriptionsRequest(userId: String): Request {
        return Request.Builder()
            .url(prepareUserSubscriptionsUrl(userId))
            .build()
    }

    fun prepareCartOffersUrl(cartId: String) = "http://localhost:8080/api/v1/carts/${cartId}"

    fun prepareDeliveryMethodsUrl(cartId: String) = "http://localhost:8080/api/v1/delivery-methods/${cartId}"

    fun preparePaymentMethodsUrl(cartId: String) = "http://localhost:8080/api/v1/payment-methods/${cartId}"

    fun prepareUserSubscriptionsUrl(userId: String) = "http://localhost:8080/api/v1/users/subscriptions/${userId}"
}

