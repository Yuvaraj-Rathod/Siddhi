package com.edtech.siddhi.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.annotation.Signed
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object NetworkModule  {
    private const val BASE_URL = "https://alfa-leetcode-api.onrender.com/"

    @Provides
    @Singleton
    fun ProvideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun ProvideLeetcodeApiService(retrofit : Retrofit)  : LeetcodeApiService =
        retrofit.create(LeetcodeApiService :: class.java)
}