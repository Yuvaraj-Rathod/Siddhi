package com.edtech.siddhi.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edtech.siddhi.screens.homescreen.HomeScreen
import com.edtech.siddhi.screens.LoginScreen
import com.edtech.siddhi.screens.RegistrationScreen

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController,"home"){
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("register") {
            RegistrationScreen(navController = navController)
        }
        composable("login") {
                LoginScreen(navController = navController)
            }

        }
}