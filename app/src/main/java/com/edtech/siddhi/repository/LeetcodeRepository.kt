package com.edtech.siddhi.repository

import com.edtech.siddhi.api.LeetcodeApiService
import com.edtech.siddhi.api.LeetcodeProfile
import com.edtech.siddhi.api.UserDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeetcodeRepository @Inject constructor(
    private val leetcodeApiService: LeetcodeApiService
) {
    suspend fun getProfileDetails(username: String): LeetcodeProfile? {
        return try {
            val response = leetcodeApiService.getUserProfile(username)
            response
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getUserDetails(username: String) : UserDetail? {
        return try{
            val response = leetcodeApiService.getUser(username)
            response
        }catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}

