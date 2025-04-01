package com.edtech.siddhi.screens

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.edtech.siddhi.R // Make sure to replace with your actual R package
import com.edtech.siddhi.ui.theme.SoftCaramel
import com.edtech.siddhi.ui.theme.RaisinBlack
import com.edtech.siddhi.ui.theme.Silver
import com.edtech.siddhi.ui.theme.DarkOnyx
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.compose.LottieConstants

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF181C14)) // Gradient background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
             Spacer(modifier = Modifier.height(30.dp))
            // **App Logo or Image**
//            Image(
//                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Use your app logo
//                contentDescription = "App Logo",
//                modifier = Modifier.size(120.dp)
//            )
            // **Welcome Text**
            Text(
                text = "Welcome to Siddhi!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = SoftCaramel
            )

            Spacer(modifier = Modifier.height(8.dp))

            // **Introductory Text**
            Text(
                text = "Your go-to app for placement preparation, with everything you need to succeed!",
                fontSize = 17.sp,
                color = Silver,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // **Lottie Animation (for fun, engagement)**
            val composition by rememberLottieComposition(LottieCompositionSpec.Asset("profile.json"))
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(400.dp)
            )

            Spacer(modifier = Modifier.height(70.dp))

            // **Get Started Button**
            Button(
                onClick = {
                    navController.navigate("register")
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SoftCaramel),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Get Started", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // **Sign up or Login Text**
            Row(horizontalArrangement = Arrangement.Center) {
                Text("Already have an account? ", color = Silver,fontSize = 15.sp)
                Text(
                    text = "Login",
                    color = SoftCaramel,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.clickable { navController.navigate("login") }
                )
            }
        }
    }
}

