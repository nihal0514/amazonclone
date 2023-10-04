package com.example.amazonclone.retrofit

import com.example.amazonclone.model.banner.BannerListResponse
import com.example.amazonclone.model.category.CategoryListResponse
import com.example.amazonclone.model.login.LoginRequest
import com.example.amazonclone.model.login.RegisterRequest
import com.example.amazonclone.model.login.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("category/getcategory/")
    suspend fun getAllCategory():Response<CategoryListResponse>

    @GET("banner/getbanner/")
    suspend fun getAllBanner():Response<BannerListResponse>

    @POST("auth/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<RegisterResponse>

}