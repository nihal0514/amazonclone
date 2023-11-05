package com.example.amazonclone.model.order

import com.google.gson.annotations.SerializedName

data class SetOrderResponse(

	@field:SerializedName("success")
	val success: Boolean? = null
)
