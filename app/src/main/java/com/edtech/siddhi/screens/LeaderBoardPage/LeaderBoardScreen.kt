package com.edtech.siddhi.screens.LeaderBoardPage

import android.text.Layout.Alignment
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.edtech.siddhi.ui.theme.*
import com.edtech.siddhi.model.UserRank
@Composable
fun LeaderboardScreen(navController: NavController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Region", "National", "Global")

    val fadeInAnim = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        fadeInAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Siddhi dark theme background
            .graphicsLayer { alpha = fadeInAnim.value }
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp) // Added top padding to clear the status bar
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)// enough height to center vertically
                .padding(top = 30.dp)
        ) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                )
            }
            Spacer(modifier = Modifier.width(60.dp))
            Text(text = "Leaderboard" , fontSize =  25.sp, color = Color.White)

        }

        Spacer(modifier = Modifier.height(20.dp))

//        TabRow(
//            selectedTabIndex = selectedTabIndex,
//            containerColor = Color(0xFF1C1C1E),
//            contentColor = Color.White,
//            indicator = { tabPositions ->
//                TabRowDefaults.Indicator(
//                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
//                    color = Color(0xFF5A5AFA)
//                )
//            }
//        ) {
//            tabTitles.forEachIndexed { index, title ->
//                Tab(
//                    selected = selectedTabIndex == index,
//                    onClick = { selectedTabIndex = index },
//                    text = {
//                        Text(
//                            text = title,
//                            color = if (selectedTabIndex == index) Color.White else Color.Gray
//                        )
//                    }
//                )
//            }
//        }

        Spacer(modifier = Modifier.height(20.dp))

        TopThreeUsers()

        Spacer(modifier = Modifier.height(16.dp))

        LeaderboardList()
    }
}
