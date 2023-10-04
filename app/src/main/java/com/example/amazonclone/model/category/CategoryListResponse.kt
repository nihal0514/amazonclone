package com.example.amazonclone.model.category

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CategoryListResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("categoryList")
	val categoryList: List<CategoryListItem>?= null
)

@Entity
data class CategoryListItem(

	@PrimaryKey
	@field:SerializedName("__v")
	val v: Int? = null,


	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
