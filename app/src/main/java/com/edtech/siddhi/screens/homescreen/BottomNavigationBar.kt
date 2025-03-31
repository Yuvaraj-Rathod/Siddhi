package com.edtech.siddhi.screens.homescreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.edtech.siddhi.ui.theme.CadetGray
import com.edtech.siddhi.ui.theme.DarkOnyx

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

sealed class NavigationItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : NavigationItem("Home", Icons.Default.Home, "home")
    object Profile : NavigationItem("Profile", Icons.Default.Person, "profile")
    object Settings : NavigationItem("Settings", Icons.Default.Settings, "settings")
}
