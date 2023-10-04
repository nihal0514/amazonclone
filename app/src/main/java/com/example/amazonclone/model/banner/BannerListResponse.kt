package com.example.amazonclone.model.banner

import com.google.gson.annotations.SerializedName

data class BannerListResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("BannerList")
	val bannerList: List<BannerListItem>? = null
)

data class BannerListItem(

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
