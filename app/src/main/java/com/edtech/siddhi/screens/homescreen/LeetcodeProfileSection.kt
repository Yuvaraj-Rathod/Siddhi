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
import android.net.Uri
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
import coil.compose.rememberImagePainter

@Composable
fun LeetCodeProfileSection(username: String) {
    var profileData by remember { mutableStateOf<LeetCodeProfile?>(null) }
    val context = LocalContext.current

    // Fetch profile data
    LaunchedEffect(username) {
        profileData = fetchLeetCodeProfile(username)
    }

    if (profileData != null) {
        val data = profileData!!
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(DarkOnyx, RoundedCornerShape(10.dp)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Image
                Image(
                    painter = rememberImagePainter(data.profileImage),
                    contentDescription = "LeetCode Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Gray, RoundedCornerShape(50))
                )

                Spacer(modifier = Modifier.width(10.dp))

                // User Details
                Column {
                    Text(
                        text = data.username,
                        color = Silver,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Solved: ${data.totalSolved} problems",
                        color = CadetGray,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "Rank: #${data.rank}",
                        color = SoftCaramel,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW,
                                "https://leetcode.com/$username/".toUri())
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Silver)
                    ) {
                        Text(text = "View Profile", color = RaisinBlack)
                    }
                }
            }
        }
    } else {
        CircularProgressIndicator(color = Silver, modifier = Modifier.padding(16.dp))
    }
}

// Data Model
data class LeetCodeProfile(
    val username: String,
    val profileImage: String,
    val totalSolved: Int,
    val rank: Int
)

suspend fun fetchLeetCodeProfile(username: String): LeetCodeProfile? {
    return withContext(Dispatchers.IO) {
        try {
            val query = """
                {
                    matchedUser(username: "$username") {
                        username
                        profile {
                            ranking
                            userAvatar
                        }
                        submitStatsGlobal {
                            acSubmissionNum {
                                count
                            }
                        }
                    }
                }
            """.trimIndent()

            val response = URL("https://leetcode.com/graphql/")
                .openConnection()
                .apply {
                    doOutput = true
                    setRequestProperty("Content-Type", "application/json")
                }.getOutputStream().use { os ->
                    os.write("""{"query": "$query"}""".toByteArray())
                }

            val jsonResponse = URL("https://leetcode.com/graphql/").readText()
            val jsonObject = JSONObject(jsonResponse)
            val userData = jsonObject.getJSONObject("data").getJSONObject("matchedUser")
            val profile = userData.getJSONObject("profile")
            val stats = userData.getJSONObject("submitStatsGlobal")
                .getJSONArray("acSubmissionNum")
                .getJSONObject(0)
                .getInt("count")

            LeetCodeProfile(
                username = userData.getString("username"),
                profileImage = profile.getString("userAvatar"),
                totalSolved = stats,
                rank = profile.getInt("ranking")
            )
        } catch (e: Exception) {
            println("Error fetching LeetCode profile: ${e.message}")
            null
        }
    }
}

