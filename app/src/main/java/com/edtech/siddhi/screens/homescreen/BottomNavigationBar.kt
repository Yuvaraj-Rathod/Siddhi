package com.edtech.siddhi.screens.homescreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home

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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*

import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow

import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Book
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edtech.siddhi.ui.theme.DarkOnyx
import com.edtech.siddhi.ui.theme.RaisinBlack
import com.edtech.siddhi.ui.theme.SoftCaramel

// Define bottom navigation items
data class BottomNavItem(val route: String, val title: String, val icon: ImageVector)

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("home", "Home", Icons.Default.Home),
        BottomNavItem("bot", "Bot", Icons.Default.Android),
        BottomNavItem("dsa", "DSA", Icons.Default.Book)
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(
                brush = Brush.horizontalGradient( // Gradient from left to right
                    colors = listOf(RaisinBlack, DarkOnyx)
                )
            )
            .blur(10.dp) // Glass effect
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent // Keeps gradient visible
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            navController.navigate(item.route)
                        }
                    },
                    icon = {
                        Icon(
                            item.icon,
                            contentDescription = item.title,
                            tint = if (isSelected) Color.Black else Color.Gray, // Highlight effect
                            modifier = Modifier.graphicsLayer(
                                scaleX = if (isSelected) 1.2f else 1.0f,
                                scaleY = if (isSelected) 1.2f else 1.0f
                            ).animateContentSize()
                        )
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }
}

