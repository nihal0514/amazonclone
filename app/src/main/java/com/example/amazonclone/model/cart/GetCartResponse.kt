package com.example.amazonclone.model.cart

import com.google.gson.annotations.SerializedName

data class GetCartResponse(

	@field:SerializedName("owner")
	val owner: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("bill")
	val bill: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("items")
	val items: List<ItemsItems>? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class ItemId(

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("_id")
	val id: String? = null
)

data class ItemsItems(

	@field:SerializedName("itemId")
	val itemId: ItemId? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
