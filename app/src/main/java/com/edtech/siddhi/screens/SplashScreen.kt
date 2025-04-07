package com.edtech.siddhi.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.edtech.siddhi.ui.theme.SoftCaramel
import com.edtech.siddhi.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay


val SoftCaramel = Color(0xFFF5E0C3)
val SoftCaramelGradient = Brush.verticalGradient(
    colors = listOf(SoftCaramel, Color(0xFFF3D9B1)) // Slightly darker variant at the bottom
)

@Composable
fun SplashScreen(navController: NavController, authViewModel : AuthViewModel) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(2000)

        val currentUser = authViewModel.getCurrentUser()
        if (currentUser != null) {
            navController.navigate("home") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("welcome") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("splash.json"))
    val progress by animateLottieCompositionAsState(
        composition,
        speed = 2f
    )

    LaunchedEffect(progress) {
        if (progress == 1f) {
            navController.navigate("welcome") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftCaramelGradient),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(180.dp)
        )
    }
}
