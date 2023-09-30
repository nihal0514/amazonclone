package com.example.amazonclone.retrofit

import com.example.amazonclone.model.CategoryListItem
import com.example.amazonclone.model.CategoryListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {

    @GET("category/getcategory/")
    suspend fun getAllCategory():Response<CategoryListResponse>
}