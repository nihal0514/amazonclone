package com.example.amazonclone.model.login

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("token")
	val token: String? = null
)

data class User(

	@field:SerializedName("name")
	val name: String? = null
)
