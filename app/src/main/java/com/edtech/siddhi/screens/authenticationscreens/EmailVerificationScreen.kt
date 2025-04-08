package com.edtech.siddhi.screens.authenticationscreens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.edtech.siddhi.ui.theme.*
import com.edtech.siddhi.viewmodel.AuthViewModel
import com.edtech.siddhi.viewmodel.AuthState


@Composable
fun EmailVerificationScreen(navController: NavController, authViewModel: AuthViewModel) {
    val context = LocalContext.current
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (val state = authState.value) {
            is AuthState.Authenticated -> {
                Toast.makeText(context, "Email verified! Logging in...", Toast.LENGTH_SHORT).show()
                navController.navigate("home") {
                    popUpTo("verification") { inclusive = true }
                }
            }
            is AuthState.EmailVerificationSent -> {
                Toast.makeText(context, "Verification email sent again!", Toast.LENGTH_SHORT).show()
            }
            is AuthState.Error -> {
                Toast.makeText(context, state.msg, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181C14)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Icon",
                tint = SoftCaramel,
                modifier = Modifier.size(72.dp)
            )

            Text(
                text = "Verify your email",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Silver
            )

            Text(
                text = "We’ve sent a verification email to your inbox. Please verify to continue.",
                color = CadetGray,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )

            Button(
                onClick = { authViewModel.reloadAndCheckEmailVerification() },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SoftCaramel),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("I have verified", color = RaisinBlack, fontSize = 16.sp)
            }

            TextButton(onClick = { authViewModel.resendVerificationEmail() }) {
                Text("Resend verification email", color = Silver)
            }

            TextButton(onClick = {
                val intent = Intent(Intent.ACTION_MAIN).apply {
                    addCategory(Intent.CATEGORY_APP_EMAIL)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            }) {
                Text("Open email app", color = Silver)
            }

            Divider(
                color = Color.Gray.copy(alpha = 0.3f),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            TextButton(onClick = {
                navController.navigate("register") {
                    popUpTo("email_verification") { inclusive = true }
                }
            }) {
                Text("Back to Registration", color = CadetGray)
            }
        }

        if (authState.value is AuthState.Loading) {
            LoadingDialog()
        }
    }
}

