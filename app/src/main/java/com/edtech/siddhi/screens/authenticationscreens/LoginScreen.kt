package com.edtech.siddhi.screens.authenticationscreens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.edtech.siddhi.ui.theme.*
import com.edtech.siddhi.utils.Validations

@SuppressLint("ShowToast")
@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavController) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF181C14))
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF181C14))
                .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
                .padding(24.dp)
        ) {
            Text(
                text = "Welcome Back",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Silver
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Login to continue",
                style = MaterialTheme.typography.bodyMedium,
                color = CadetGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Email Input
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = !Validations.isValidEmail(email)
                },
                label = { Text("Email", color = Silver) },
                textStyle = TextStyle(color = Silver),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                leadingIcon = {
                    Icon(Icons.Filled.Email, contentDescription = "Email", tint = Silver, modifier = Modifier.size(18.dp))
                },
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth(),
                isError = emailError
            )
            if (emailError) Text("Enter a valid Gmail ID", color = Color.Red, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(12.dp))

            // Password Input
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = !Validations.isValidPassword(password)
                },
                label = { Text("Password", color = Silver) },
                textStyle = TextStyle(color = Silver),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                leadingIcon = {
                    Icon(Icons.Filled.Lock, contentDescription = "Password", tint = Silver, modifier = Modifier.size(18.dp))
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle Password Visibility",
                            tint = Silver,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth(),
                isError = passwordError
            )
            if (passwordError) Text("Password must be at least 6 characters and contain a lowercase, uppercase, and special character.", color = Color.Red, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(20.dp))

            // Login Button
            Button(
                onClick = {
                    if (emailError || passwordError) {
                        Toast.makeText(context, "Please fix the errors", Toast.LENGTH_SHORT).show()
                    } else {
                        navController.navigate("home")
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SoftCaramel),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp, pressedElevation = 2.dp)
            ) {
                Text(text = "Login", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = RaisinBlack)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Forgot Password
            TextButton(onClick = { /* Forgot Password Action */ }) {
                Text("Forgot Password?", color = CadetGray, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Create a new account? Register
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create a new account?", color = CadetGray, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Register",
                    color = Silver,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { navController.navigate("register") }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLogin() {
    LoginScreen(navController = rememberNavController())
}