package com.example.amazonclone.model.address

import com.google.gson.annotations.SerializedName

data class AddressesResponse(

	@field:SerializedName("Addresses")
	val addresses: Addresses? = null
)

data class AddressItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("pin")
	val pin: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mobile")
	val mobile: Long? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
)

data class Addresses(

    @field:SerializedName("owner")
	val owner: String? = null,

    @field:SerializedName("Address")
	val address: List<AddressItem>? = null,

    @field:SerializedName("__v")
	val v: Int? = null,

    @field:SerializedName("_id")
	val id: String? = null
)
