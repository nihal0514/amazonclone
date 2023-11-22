package com.example.amazonclone.di

import com.example.amazonclone.api.ApiHelper
import com.example.amazonclone.api.ApiHelperImpl
import com.example.amazonclone.api.ApiService
import com.example.amazonclone.utils.Constants
import com.example.amazonclone.utils.Constants.BASE_URL
import com.stripe.android.core.networking.RequestHeadersFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
object NetworkModule {

    fun ProvideBaseUrl() = Constants.BASE_URL
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)



        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

   /* @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }*/

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

}