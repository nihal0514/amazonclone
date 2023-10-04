package com.example.amazonclone.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.amazonclone.db.CategoryDB
import com.example.amazonclone.model.banner.BannerListItem
import com.example.amazonclone.model.category.CategoryListItem
import com.example.amazonclone.model.login.LoginRequest
import com.example.amazonclone.model.login.RegisterRequest
import com.example.amazonclone.model.login.RegisterResponse
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
}