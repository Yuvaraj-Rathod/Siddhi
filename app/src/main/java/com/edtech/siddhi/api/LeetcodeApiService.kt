package com.edtech.siddhi.api

import retrofit2.http.GET
import retrofit2.http.Path


interface LeetcodeApiService {

    @GET("userProfile/{username}")
    suspend fun getUserProfile(
        @Path("username") username : String
   ) : LeetcodeProfile

   @GET("/{username}")
   suspend fun getUser(
       @Path("username") username: String
   ) : UserDetail
}