package com.edtech.siddhi.utils

import android.annotation.SuppressLint
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

@SuppressLint("StaticFieldLeak")
object Constants {

     private val remoteConfig = FirebaseRemoteConfig.getInstance()
     val apiKey : String
          get() = remoteConfig.getString("GEMINI_API")

}