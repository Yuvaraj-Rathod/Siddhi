package com.edtech.siddhi.screens.LeaderBoardPage


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edtech.siddhi.R

@Composable
fun LeaderboardList() {
    val users = listOf(
        Triple("Sebastian", 1124, true),
        Triple("Jason", 875, false),
        Triple("Natalie", 774, true),
        Triple("Serenity", 723, true)
    )

    Column {
        users.forEachIndexed { index, (name, score, isUp) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "#${index + 4}",
                    color = Color.Gray,
                    modifier = Modifier.width(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.img_3),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = name, color = Color.White)
                    Text(text = "@username", color = Color.Gray, fontSize = 12.sp)
                }
                Text(text = "$score", color = Color.White)
                Icon(
                    imageVector = if (isUp) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = if (isUp) Color.Green else Color.Red
                )
            }
        }
    }
}
