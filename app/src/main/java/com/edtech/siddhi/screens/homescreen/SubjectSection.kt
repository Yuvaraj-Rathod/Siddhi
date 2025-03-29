package com.edtech.siddhi.screens.homescreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
        CardData(videoTitle = "Operating System", channelName = "Love Babbar", videos = 45) {
            navController.navigate("home")
        },
        CardData(videoTitle = "Computer Networks", channelName = "KnowledgeGATE", videos = 40) {
            navController.navigate("home")
        },
        CardData(videoTitle = "Data Structures & Algorithms", channelName = "Apna College", videos = 60) {
            navController.navigate("home")
        },
        CardData(videoTitle = "Database Management", channelName = "Code Help", videos = 50) {
            navController.navigate("home")
        },
        CardData(videoTitle = "Guess the Output", channelName = "Geeks For Geeks", videos = 25) {
            navController.navigate("home")
        }
    )

    Column(modifier = modifier.padding(top = 18.dp)) {
        Text(
            text = "📚 Core Topics",
            color = Silver,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 8.dp, bottom = 10.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
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
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = DarkOnyx),
        modifier = Modifier
            .width(220.dp)
            .height(75.dp)
            .clickable { cardData.onClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = cardData.videoTitle,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Silver,
                        maxLines = 1
                    )
                    Text(
                        text = "By ${cardData.channelName}",
                        fontSize = 11.sp,
                        color = CadetGray
                    )
                }
            }

            // Video count and icon at the bottom-right corner
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 4.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayCircle,
                    contentDescription = "Video Icon",
                    tint = Silver,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${cardData.videos}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Silver
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