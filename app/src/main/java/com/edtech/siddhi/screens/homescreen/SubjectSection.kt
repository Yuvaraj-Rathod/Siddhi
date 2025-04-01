package com.edtech.siddhi.screens.homescreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.edtech.siddhi.model.CardData
import com.edtech.siddhi.ui.theme.*

@Composable
fun SubjectSection(modifier: Modifier = Modifier, navController: NavController) {
    val videoList = listOf(
        CardData(videoTitle = "Data Structures & Algorithms", channelName = "Apna College", videos = 60) {
            navController.navigate("dsa")
        },
        CardData(videoTitle = "Database Management", channelName = "Code Help", videos = 50) {
            navController.navigate("dbms")
        },
        CardData(videoTitle = "Operating System", channelName = "Love Babbar", videos = 45) {
            navController.navigate("os")
        },
        CardData(videoTitle = "Computer Networks", channelName = "KnowledgeGATE", videos = 40) {
            navController.navigate("cn")
        },
        CardData(videoTitle = "Guess the Output", channelName = "Geeks For Geeks", videos = 25) {
            navController.navigate("csnippet")
        }
    )

    Column(modifier = modifier.padding(top = 18.dp)) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(videoList) { item ->
                SubjectCard(item)
            }
        }
    }
}

@Composable
fun SubjectCard(cardData: CardData) {
    Card(
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        modifier = Modifier
            .width(250.dp)
            .height(100.dp)
            .clickable { cardData.onClick() }
    ) {
        // 🌌 Deep Midnight Blue to Cool Cyan Gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.sweepGradient(
                        colors = listOf(DarkOnyx, Onyx) // Midnight Blue to Cyan
                    )
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = cardData.videoTitle,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White, // Pure White for sharp contrast
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "By ${cardData.channelName}",
                        fontSize = 12.sp,
                        color = Color(0xFFB0BEC5) // Subtle Gray-Blue
                    )
                }
            }

            // Play Icon & Video Count
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 6.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayCircle,
                    contentDescription = "Video Icon",
                    tint = Color(0xFFFFC857), // ⚡ Electric Gold for emphasis
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${cardData.videos}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SubjectSection(navController = rememberNavController())
}