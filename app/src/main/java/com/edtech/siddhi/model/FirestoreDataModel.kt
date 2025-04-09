package com.edtech.siddhi.model

data class UserFireStore(
    val uid: String = "",
    val username: String = "",
    val email: String = "",
    val leetcodeId: String = "",
    val likedVideos: List<String> = emptyList(),
    val watchedVideos: List<String> = emptyList()
)
