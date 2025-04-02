package com.edtech.siddhi.skeletalloading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun LeetCodeProfileSkeleton() {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF303030)),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ShimmerBox(height = 55.dp, width = 80.dp)
                    ShimmerBox(height = 55.dp, width = 80.dp)
                }
                Spacer(modifier = Modifier.height(12.dp))
                ShimmerBox(height = 55.dp, width = 80.dp)
            }

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                ShimmerBox(height = 140.dp, width = 140.dp, shape = RoundedCornerShape(60))
            }
        }
    }
}
