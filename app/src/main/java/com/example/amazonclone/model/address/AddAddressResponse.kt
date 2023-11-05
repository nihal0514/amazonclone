package com.example.amazonclone.model.address

import com.google.gson.annotations.SerializedName

data class AddAddressResponse(

	@field:SerializedName("address")
	val address: Address? = null
)

data class Address(

	@field:SerializedName("owner")
	val owner: String? = null,

	@field:SerializedName("Address")
	val address: List<AddressItem?>? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
