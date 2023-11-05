package com.example.amazonclone.model.stripe

import com.google.gson.annotations.SerializedName

data class StripeServerModel(

    @field:SerializedName("publishableKey")
    val publishableKey: String? = null,

    @field:SerializedName("ephemeralKey")
    val ephemeralKey: String? = null,

    @field:SerializedName("paymentIntent")
    val paymentIntent: String? = null,

    @field:SerializedName("customer")
    val customer: String? = null
)
