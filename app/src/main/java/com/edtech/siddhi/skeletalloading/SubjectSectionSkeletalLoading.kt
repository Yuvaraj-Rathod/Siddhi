package com.edtech.siddhi.skeletalloading


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun SubjectSkeleton() {
    LazyRow(
        modifier = Modifier.padding(top = 25.dp).height(80.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        items(3) {
            ShimmerBox(height = 120.dp, width = 250.dp, shape = RoundedCornerShape(14.dp))
        }
    }
}
