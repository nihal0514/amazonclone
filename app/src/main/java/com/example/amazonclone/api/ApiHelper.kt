package com.example.amazonclone.api

import com.example.amazonclone.model.address.AddAddressRequest
import com.example.amazonclone.model.address.AddAddressResponse
import com.example.amazonclone.model.address.AddressesResponse
import com.example.amazonclone.model.banner.BannerListResponse
import com.example.amazonclone.model.cart.CartRequest
import com.example.amazonclone.model.cart.CartResponse
import com.example.amazonclone.model.cart.GetCartResponse
import com.example.amazonclone.model.category.CategoryListResponse
import com.example.amazonclone.model.login.LoginRequest
import com.example.amazonclone.model.login.RegisterRequest
import com.example.amazonclone.model.login.RegisterResponse
import com.example.amazonclone.model.order.SetOrderResponse
import com.example.amazonclone.model.products.ProdByIdResponse
import com.example.amazonclone.model.products.ProdListResponse
import com.example.amazonclone.model.stripe.StripeServerModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiHelper {

    fun getAllCategory(): Observable<CategoryListResponse>

    fun getAllBanner(): Observable<BannerListResponse>

    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<RegisterResponse>

    fun getProducts(): Observable<ProdListResponse>

    fun getProductsById(@Path("id") id: String): Observable<ProdByIdResponse>

    fun addtoCart(@Body cartRequest: CartRequest, @Header("Authorization")token: String ): Observable<CartResponse>

    fun getCart(@Header("Authorization")token: String): Observable<GetCartResponse>

    fun getAddresses(  @Header("Authorization")token: String ): Observable<AddressesResponse>

    fun addNewAddress(@Body addAddressRequest: AddAddressRequest, @Header("Authorization")token: String ): Observable<AddAddressResponse>

    fun doOrderPayment(@Header("Authorization")token: String): Observable<StripeServerModel>


    fun setOrder(  @Header("Authorization")token: String): Observable<SetOrderResponse>

    fun deleteCart( @Header("Authorization")token: String ): Observable<SetOrderResponse>


}