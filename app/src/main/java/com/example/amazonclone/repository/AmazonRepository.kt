package com.example.amazonclone.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.amazonclone.db.CategoryDB
import com.example.amazonclone.model.banner.BannerListItem
import com.example.amazonclone.model.cart.CartRequest
import com.example.amazonclone.model.cart.CartResponse
import com.example.amazonclone.model.cart.GetCartResponse
import com.example.amazonclone.model.category.CategoryListItem
import com.example.amazonclone.model.login.LoginRequest
import com.example.amazonclone.model.login.RegisterRequest
import com.example.amazonclone.model.login.RegisterResponse
import com.example.amazonclone.model.products.ProdListItem
import com.example.amazonclone.model.products.Product
import com.example.amazonclone.retrofit.ApiInterface
import javax.inject.Inject

class AmazonRepository @Inject constructor(
    private val categoryDB: CategoryDB,
    private val apiInterface: ApiInterface
) {
    private val _categoryDeals= MutableLiveData<List<CategoryListItem>>()
    val categoryDeals: LiveData<List<CategoryListItem>> = _categoryDeals

    suspend fun getCategories(){

        val result= apiInterface.getAllCategory()
        Log.d("mm",result.body().toString());
        result.code()
        if(result.isSuccessful){
            result.body().let {
                categoryDB.getCategoryDao().addCategory(it?.categoryList ?: emptyList())
                  _categoryDeals.postValue(it?.categoryList ?: emptyList())

                //for room db
               // _categoryDeals.postValue(categoryDB.getCategoryDao().getCategories())
            }
        }
    }

    private val _bannerItems= MutableLiveData<List<BannerListItem>>()
    val bannerItems: LiveData<List<BannerListItem>> = _bannerItems

    suspend fun getBannerImages(){

        val result= apiInterface.getAllBanner()
        if(result.isSuccessful){
            result.body().let {
                _bannerItems.postValue(it?.bannerList ?: emptyList())
            }
        }
    }

    private val _registerResponse= MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    suspend fun registerUser(registerRequest: RegisterRequest){
        val result= apiInterface.registerUser(registerRequest)
        if(result.isSuccessful){
            result.body().let {
                _registerResponse.postValue(it)
            }
        }
    }


    private val _loginResponse= MutableLiveData<RegisterResponse>()
    val loginResponse: LiveData<RegisterResponse> = _loginResponse

    suspend fun loginUser(loginRequest: LoginRequest){
        val result= apiInterface.loginUser(loginRequest)
        if(result.isSuccessful){
            result.body().let {
                _loginResponse.postValue(it)
            }
        }
    }

    private val _prodListData= MutableLiveData<List<ProdListItem>>()
    val prodListData: LiveData<List<ProdListItem>> = _prodListData

    suspend fun getproducts(){
        val result= apiInterface.getProducts()
        if(result.isSuccessful){
            result.body().let {
                _prodListData.postValue(it?.prodList ?: emptyList())
            }
        }
    }

    private val _prodListByIdData= MutableLiveData<Product>()
    val prodListByIdData: LiveData<Product> = _prodListByIdData

    suspend fun getproductsByID(id: String){
        val result= apiInterface.getProductsById(id)
        if(result.isSuccessful){
            result.body().let {
                _prodListByIdData.postValue(it?.product !!)
            }
        }
    }

    private val _addtoCartData= MutableLiveData<CartResponse>()
    val addtoCartData: LiveData<CartResponse> = _addtoCartData

    suspend fun addToCart(cartRequest: CartRequest,token:String){
        val result= apiInterface.addtoCart(cartRequest,token)

        if(result.isSuccessful){
            result.body().let {
                _addtoCartData.postValue(it)
            }
        }
    }

    private val _getCartData= MutableLiveData<GetCartResponse>()
    val getCartData: LiveData<GetCartResponse> = _getCartData

    suspend fun getCartData(token: String){
        val result= apiInterface.getCart(token);
        if(result.isSuccessful){
            result.body().let {
                _getCartData.postValue(it)
            }
        }
    }
}