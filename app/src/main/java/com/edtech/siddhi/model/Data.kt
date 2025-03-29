package com.edtech.siddhi.model

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class CodingPlatform(
    val name: String,
    val description: String,
    val gradient: Brush,
    val url: String
)

data class CardData(
    val videoTitle: String,
    val channelName: String,
    val videos: Int, // Total videos in the playlist
    val onClick: () -> Unit
)