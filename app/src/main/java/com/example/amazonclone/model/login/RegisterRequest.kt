package com.example.amazonclone.model.login

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

	@field:SerializedName("password")
	var password: String? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("email")
	var email: String? = null
)
