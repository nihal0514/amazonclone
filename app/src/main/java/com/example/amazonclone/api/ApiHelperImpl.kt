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
import com.example.amazonclone.utils.apiUtilities
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) :ApiHelper{
    override fun getAllCategory(): Observable<CategoryListResponse> = apiService.getAllCategory()

    override fun getAllBanner(): Observable<BannerListResponse> = apiService.getAllBanner()
    override suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse> = apiService.registerUser(registerRequest)

    override suspend fun loginUser(@Body loginRequest: LoginRequest): Response<RegisterResponse> = apiService.loginUser(loginRequest = loginRequest)

    override fun getProducts(): Observable<ProdListResponse> = apiService.getProducts()

    override fun getProductsById(@Path("id") id: String): Observable<ProdByIdResponse> =apiService.getProductsById(id =  id)

    override fun addtoCart(
        @Body cartRequest: CartRequest,
        @Header(value = "Authorization") token: String
    ): Observable<CartResponse> = apiService.addtoCart(cartRequest,token)

    override fun getCart(@Header(value = "Authorization") token: String): Observable<GetCartResponse> = apiService.getCart(token)

    override fun getAddresses(@Header(value = "Authorization") token: String): Observable<AddressesResponse> =apiService.getAddresses(token)

    override fun addNewAddress(
        @Body addAddressRequest: AddAddressRequest,
        @Header(value = "Authorization") token: String
    ): Observable<AddAddressResponse> = apiService.addNewAddress(addAddressRequest, token)

    override fun doOrderPayment(@Header(value = "Authorization") token: String): Observable<StripeServerModel> =apiService.doOrderPayment(token)


    override fun setOrder(@Header(value = "Authorization") token: String): Observable<SetOrderResponse> =apiService.setOrder(token)

    override fun deleteCart(@Header(value = "Authorization") token: String): Observable<SetOrderResponse> =apiService.deleteCart(token)


}