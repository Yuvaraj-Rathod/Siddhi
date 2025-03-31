package com.edtech.siddhi.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent
import android.graphics.Paint.Align
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import com.edtech.siddhi.ui.theme.Silver
import com.edtech.siddhi.ui.theme.DarkOnyx
import com.edtech.siddhi.ui.theme.RaisinBlack
import com.edtech.siddhi.ui.theme.SoftCaramel
import com.edtech.siddhi.ui.theme.CadetGray
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.edtech.siddhi.viewmodel.LeetcodeViewModel

@Composable
fun LeetCodeProfileSection(
    viewModel: LeetcodeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val profile by viewModel.profile.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProfile(username = "Yuvaraj_Rathod_")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (profile == null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = Silver)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Loading Profile...",
                    color = Silver,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            val data = profile!!

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkOnyx),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // ⬆ Increased height
                    .padding(vertical = 8.dp) // ⬆ Added vertical padding for better spacing
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp), // ⬆ Increased padding for a more open feel
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 🔹 Problem Stats in a Column
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // 🔹 First Row (Easy & Medium)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            ProblemSolvedBox("Easy", data.easySolved, data.totalEasy, Color(0xFF00A676))
                            ProblemSolvedBox("Medium", data.mediumSolved, data.totalMedium, Color(0xFFFFC107))
                        }

                        Spacer(modifier = Modifier.height(12.dp)) // ⬆ More spacing

                        // 🔹 Second Row (Hard - Centered)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            ProblemSolvedBox("Hard", data.hardSolved, data.totalHard, Color(0xFFE63946))
                        }
                    }

                    // 🔹 Circular Progress in the Center
                    Box(
                        modifier = Modifier
                            .size(150.dp) // ⬆ Made it larger
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.size(150.dp)) { // ⬆ Slightly larger circle
                            val progress = data.totalSolved.toFloat() / data.totalQuestions.toFloat()

                            drawArc(
                                color = Color.Gray,
                                startAngle = 270f,
                                sweepAngle = 360f,
                                useCenter = false,
                                style = Stroke(width = 14.dp.toPx(), cap = StrokeCap.Round)
                            )

                            drawArc(
                                color = Color.Red,
                                startAngle = 270f,
                                sweepAngle = 360 * progress,
                                useCenter = false,
                                style = Stroke(width = 14.dp.toPx(), cap = StrokeCap.Round)
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${data.totalSolved}",
                                fontSize = 24.sp, // ⬆ Larger text
                                fontWeight = FontWeight.Bold,
                                color = Silver
                            )
                            Text(
                                text = "${data.totalQuestions}",
                                fontSize = 14.sp, // ⬆ Slightly larger
                                color = CadetGray
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ProblemSolvedBox(level: String, solved: Int, total: Int, color: Color) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = DarkOnyx),
        elevation = CardDefaults.cardElevation(6.dp), // 🔹 Adds elevation
        modifier = Modifier.padding(4.dp) // 🔹 Adds spacing around each box
    ) {
        Box(
            modifier = Modifier
                .padding(12.dp) // 🔹 Padding inside the box
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = level, color = color, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text = "$solved / $total", color = Silver, fontSize = 11.sp)
            }
        }
    }
}



