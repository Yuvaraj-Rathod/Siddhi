package com.edtech.siddhi.screens.homescreen
import CodingPlatformSection
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.edtech.siddhi.ui.theme.*
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.edtech.siddhi.api.UserDetail
import com.edtech.siddhi.viewmodel.LeetcodeViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.swiperefresh.SwipeRefresh
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun ProfileSection(user: UserDetail?,navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 2.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .background(CadetGray, shape = CircleShape)
                    .border(2.dp, Silver, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (user?.avatar != null) {
                    Image(
                        painter = rememberImagePainter(user.avatar),
                        contentDescription = "Profile Avatar",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile Icon",
                        tint = Silver,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = user?.name ?: "Yuvaraj",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )

                Text(
                    text = user?.username ?: "yrrathod0@gmail.com",
                    color = CadetGray,
                    fontSize = 14.sp
                )
            }

            IconButton(
                onClick = { navController.navigate("home") }
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Menu",
                    tint = Silver,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionPreview() {
    val dummyUser = UserDetail(
        avatar = "https://via.placeholder.com/150", // Dummy image URL
        birthday = "01-01-2000",
        company = "Google",
        country = "India",
        name = "Priyanshu Kumar",
        school = "IIT Bombay",
        twitter = "@priyanshu",
        username = "priyanshu2202k"
    )
    ProfileSection(user = dummyUser, navController = rememberNavController())
}
