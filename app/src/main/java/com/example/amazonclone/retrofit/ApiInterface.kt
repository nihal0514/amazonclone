package com.example.amazonclone.retrofit

import com.example.amazonclone.model.banner.BannerListResponse
import com.example.amazonclone.model.cart.CartRequest
import com.example.amazonclone.model.cart.CartResponse
import com.example.amazonclone.model.cart.GetCartResponse
import com.example.amazonclone.model.category.CategoryListResponse
import com.example.amazonclone.model.login.LoginRequest
import com.example.amazonclone.model.login.RegisterRequest
import com.example.amazonclone.model.login.RegisterResponse
import com.example.amazonclone.model.products.ProdByIdResponse
import com.example.amazonclone.model.products.ProdListResponse
import com.example.amazonclone.model.products.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("category/getcategory/")
    suspend fun getAllCategory():Response<CategoryListResponse>

    @GET("banner/getbanner/")
    suspend fun getAllBanner():Response<BannerListResponse>

    @POST("auth/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<RegisterResponse>

    @GET("products/getproducts")
    suspend fun getProducts(): Response<ProdListResponse>

    @GET("products/getproduct/{id}")
    suspend fun getProductsById(@Path("id") id: String): Response<ProdByIdResponse>

    @POST("cart/addtocart/")
    suspend fun addtoCart(@Body cartRequest: CartRequest, @Header("Authorization")token: String ): Response<CartResponse>

    @GET("cart/getcart/")
    suspend fun getCart( @Header("Authorization")token: String ): Response<GetCartResponse>

}