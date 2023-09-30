package com.example.amazonclone.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.amazonclone.db.CategoryDB
import com.example.amazonclone.model.CategoryListItem
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
}