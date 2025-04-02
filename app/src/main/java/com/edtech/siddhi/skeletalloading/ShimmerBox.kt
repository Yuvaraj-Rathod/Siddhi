package com.edtech.siddhi.skeletalloading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerBox(height: Dp, width: Dp, shape: RoundedCornerShape = RoundedCornerShape(8.dp)) {
    Box(
        modifier = Modifier
            .size(width, height)
            .clip(shape)
            .shimmer()
            .background(Color.Gray.copy(alpha = 0.3f))
    )
}
