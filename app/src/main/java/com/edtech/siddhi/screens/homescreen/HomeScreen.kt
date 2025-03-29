package com.edtech.siddhi.screens.homescreen

import CodingPlatformSection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.edtech.siddhi.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle FAB action */ },
                containerColor = Silver,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", tint = RaisinBlack)
            }
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            // 1️⃣ Profile Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp), // Moves it slightly to the right
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile Icon",
                    tint = Silver,
                    modifier = Modifier.size(46.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = "Yuvaraj",
                        color = Silver,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "yrrathod0@gmail.com",
                        color = CadetGray,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            // 🔥 2️⃣ LeetCode Profile Section
//            Column(
//                modifier = Modifier.fillMaxWidth().weight(1f),
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    text = "🏆 LeetCode Profile",
//                    color = Silver,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(start = 10.dp, bottom = 4.dp)
//                )
//                LeetCodeProfileSection(username = "Yuvaraj_Rathod_")
//            }

            // 3️⃣ Coding Platform Section
            Text(
                text = "\uD83D\uDE80 Coding",
                color = Silver,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, bottom = 4.dp)
            )

            CodingPlatformSection()

            Spacer(modifier = Modifier.height(8.dp))

            // 4️⃣ Core Topics Section
            SubjectSection(navController = navController)


            Spacer(modifier = Modifier.weight(1f)) // Pushes content to center
        }
    }
}



sealed class NavigationItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : NavigationItem("Home", Icons.Default.Home, "home")
    object Profile : NavigationItem("Profile", Icons.Default.Person, "profile")
    object Settings : NavigationItem("Settings", Icons.Default.Settings, "settings")
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        NavigationItem.Home,
        NavigationItem.Profile,
        NavigationItem.Settings
    )

    val currentRoute by navController.currentBackStackEntryAsState()
    val selectedIndex = navigationItems.indexOfFirst { it.route == currentRoute?.destination?.route }
        .takeIf { it != -1 } ?: 0

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),  // Reduced height
        containerColor = DarkOnyx
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    if (currentRoute?.destination?.route != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp), // Reduced icon size
                        tint = if (selectedIndex == index) Color.White else CadetGray
                    )
                },
                alwaysShowLabel = false // Hides labels for a cleaner look
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
