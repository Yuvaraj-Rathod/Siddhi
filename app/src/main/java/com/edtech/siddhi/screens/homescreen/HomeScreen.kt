package com.edtech.siddhi.screens.homescreen

import CodingPlatformSection
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.edtech.siddhi.viewmodel.LeetcodeViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.swiperefresh.SwipeRefresh
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(navController: NavController) {

    val viewModel: LeetcodeViewModel = hiltViewModel()
    val user by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getUser("Yuvaraj_Rathod")
    }

    var isRefreshing by remember { mutableStateOf(false) }
    val refreshState = rememberSwipeRefreshState(isRefreshing)

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
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 🔹 1️⃣ Profile Section with Avatar from Coil
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (user?.avatar != null) {
                    Image(
                        painter = rememberImagePainter(user!!.avatar),
                        contentDescription = "Profile Avatar",
                        modifier = Modifier
                            .size(46.dp)
                            .background(CadetGray, shape = CircleShape)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile Icon",
                        tint = Silver,
                        modifier = Modifier.size(46.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = user?.name ?: "Yuvaraj",
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

            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier.fillMaxWidth().height(220.dp)) {
                LeetCodeProfileSection(
                    viewModel = hiltViewModel(),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            CodingPlatformSection(                         //CODING
                modifier = Modifier.fillMaxWidth()
            )


            SubjectSection(                               //CORE TOPICS
                navController = navController,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


// 🔹 Navigation Item Sealed Class
sealed class NavigationItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : NavigationItem("Home", Icons.Default.Home, "home")
    object Profile : NavigationItem("Profile", Icons.Default.Person, "profile")
    object Settings : NavigationItem("Settings", Icons.Default.Settings, "settings")
}

// 🔹 Bottom Navigation Bar
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
            .height(56.dp), // Reduced height
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

// 🔹 Preview Function
@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
