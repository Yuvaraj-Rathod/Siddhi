package com.edtech.siddhi.screens.LeaderBoardPage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.edtech.siddhi.ui.theme.*
import com.edtech.siddhi.model.UserRank

@Composable
fun RankCard(user: UserRank) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF303030)),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = when (user.rank) {
                    1 -> Icons.Filled.EmojiEvents
                    2 -> Icons.Filled.MilitaryTech
                    3 -> Icons.Filled.Grade
                    else -> Icons.Filled.Leaderboard
                },
                contentDescription = "Rank Icon",
                tint = if (user.rank == 1) Color(0xFFFFD700) else Color(0xFFE3A869),
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(user.name, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(user.leetcodeId, color = Color.Gray, fontSize = 14.sp)
            }

            Text(
                text = "#${user.rank}",
                color = Color(0xFFE3A869),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}
