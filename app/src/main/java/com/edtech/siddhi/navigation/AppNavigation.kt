package com.edtech.siddhi.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edtech.siddhi.screens.SplashScreen
import com.edtech.siddhi.screens.homescreen.HomeScreen
import com.edtech.siddhi.screens.authenticationscreens.LoginScreen
import com.edtech.siddhi.screens.authenticationscreens.RegistrationScreen
import com.edtech.siddhi.screens.WelcomeScreen
import com.edtech.siddhi.screens.authenticationscreens.EmailVerificationScreen
import com.edtech.siddhi.screens.chatbotscreen.ChatScreen
import com.edtech.siddhi.screens.subject.CodeSnippetPage
import com.edtech.siddhi.viewmodel.AuthViewModel
import com.edtech.siddhi.viewmodel.ChatViewModel
import com.example.manvantara.screens.subject.CnPage
import com.example.manvantara.screens.subject.DbmsPage
import com.example.manvantara.screens.subject.DsaPage
import com.example.manvantara.screens.subject.OsPage

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun AppNavigation(modifier: Modifier = Modifier, authViewModel : AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController,"splash"){
        composable("home") {
            HomeScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("register") {
            RegistrationScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("login") {
            LoginScreen(navController = navController, authViewModel = authViewModel)
            }
        composable("bot") {
            ChatScreen(chatViewModel = ChatViewModel(), navController = navController)
        }
        composable("dbms") {
            DbmsPage(navController = navController)
        }
        composable("os") {
            OsPage(navController = navController)
        }
        composable("dsa") {
            DsaPage(navController = navController)
        }
        composable("cn") {
            CnPage(navController = navController)
        }
        composable("csnippet") {
            CodeSnippetPage(navController = navController)
        }
        composable("welcome") {
            WelcomeScreen(navController = navController)
        }
        composable("splash") {
            SplashScreen(navController = navController,authViewModel = authViewModel)
        }
        composable("verification") {
            EmailVerificationScreen(navController,authViewModel)
        }
    }
}