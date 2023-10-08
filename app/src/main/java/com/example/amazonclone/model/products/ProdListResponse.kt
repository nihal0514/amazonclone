package com.example.amazonclone.model.products

import com.google.gson.annotations.SerializedName

data class ProdListResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("prodList")
	val prodList: List<ProdListItem>? = null
)

data class ProdListItem(

	@field:SerializedName("richDescription")
	val richDescription: String? = null,

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("numReviews")
	val numReviews: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("dateCreated")
	val dateCreated: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("isFeatured")
	val isFeatured: Boolean? = null,

	@field:SerializedName("brand")
	val brand: String? = null
)

data class Category(

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
