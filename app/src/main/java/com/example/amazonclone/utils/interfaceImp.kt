package com.example.amazonclone.utils

import com.example.amazonclone.model.stripe.StripeServerModel
import retrofit2.Response
import retrofit2.http.POST

interface interfaceImp {
    @POST("payment-sheet")
    suspend fun getfromserver(): Response<StripeServerModel>
}