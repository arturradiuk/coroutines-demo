package com.aradiuk.exampleproblem

import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Response
import java.math.BigDecimal
import java.nio.charset.Charset

data class Order(
    val userId: String,
    val cartId: String,
    val chosenDeliveryMethodId: String,
    val chosenPaymentMethod: String,
    val price: BigDecimal
)

fun Response.toOffers() = Json.decodeFromString<List<Offer>>(this.body?.string() ?: "")
fun Response.toPaymentMethods() = Json.decodeFromString<List<PaymentMethod>>(this.body?.string() ?: "")
fun Response.toUserSubscriptions() = Json.decodeFromString<List<UserSubscription>>(this.body?.string() ?: "")
fun Response.toDeliveryMethods() = Json.decodeFromString<List<DeliveryMethod>>(this.body?.string() ?: "")

fun ByteBuf.toOffers() = Json.decodeFromString<List<Offer>>(this.toString(Charset.forName("utf-8")))
fun ByteBuf.toPaymentMethods() = Json.decodeFromString<List<PaymentMethod>>(this.toString(Charset.forName("utf-8")))
fun ByteBuf.toUserSubscriptions() =
    Json.decodeFromString<List<UserSubscription>>(this.toString(Charset.forName("utf-8")))
fun ByteBuf.toDeliveryMethods() = Json.decodeFromString<List<DeliveryMethod>>(this.toString(Charset.forName("utf-8")))

@Serializable
class Offer

@Serializable
class DeliveryMethod

@Serializable
class PaymentMethod

@Serializable
class UserSubscription
data class OrderData(
    val cartOffers: List<Offer>,
    val availableDeliveryMethods: List<DeliveryMethod>,
    val availablePaymentMethods: List<PaymentMethod>,
    val userSubscriptions: List<UserSubscription>
)