package com.example.amazonclone.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object apiUtilities {


    fun getApiInterface(): interfaceImp {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.41:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(interfaceImp::class.java)
    }
}