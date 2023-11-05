package com.example.amazonclone.model.address

import com.google.gson.annotations.SerializedName

data class AddAddressRequest(

	@field:SerializedName("country")
	var country: String? = null,

	@field:SerializedName("address")
	var address: String? = null,

	@field:SerializedName("pin")
	var pin: Int? = null,

	@field:SerializedName("name")
    var name: String? = null,

	@field:SerializedName("mobile")
	var mobile: Int? = null,

	@field:SerializedName("state")
	var state: String? = null
)
