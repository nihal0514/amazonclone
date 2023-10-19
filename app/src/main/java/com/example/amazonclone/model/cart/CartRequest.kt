package com.example.amazonclone.model.cart

import com.google.gson.annotations.SerializedName

data class CartRequest(

	@field:SerializedName("itemId")
	var itemId: String? = null,

	@field:SerializedName("quantity")
	var quantity: Int? = null
)
