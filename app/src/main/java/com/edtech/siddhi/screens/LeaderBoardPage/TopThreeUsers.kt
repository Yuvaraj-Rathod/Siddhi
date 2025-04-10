package com.edtech.siddhi.screens.LeaderBoardPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edtech.siddhi.R
@Composable
fun TopThreeUsers() {
    val users = listOf(
        Triple("Jackson", 1847, R.drawable.img),  // 2nd place
        Triple("Eiden", 2430, R.drawable.img_1),    // 1st place
        Triple("Emma Aria", 1674, R.drawable.img_2) // 3rd place
    )

    val ranks = listOf(2, 1, 3)
    val colors = listOf(Color(0xFF4C6FFF), Color(0xFFFFC300), Color(0xFF00C853))
    val podiumHeights = listOf(135.dp, 200.dp, 100.dp) // visually represent ranking

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        users.forEachIndexed { index, (name, score, imageRes) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .border(2.dp, colors[index], CircleShape)
                        .background(Color.DarkGray),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                    )
                }

                Text(
                    text = name,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 6.dp)
                )
                Text(
                    text = score.toString(),
                    color = colors[index],
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )

                // Podium box
                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .height(podiumHeights[index])
                        .background(color = colors[index], shape = RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "#${ranks[index]}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
