package com.edtech.siddhi.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
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
    val passToast = Toast.makeText(context, "Password should be at least 6 characters and contain a lowercase, uppercase, and special character", Toast.LENGTH_SHORT)
    val emailToast = Toast.makeText(context, "Enter a valid Gmail ID", Toast.LENGTH_SHORT)
    val bothToast = Toast.makeText(context, "Enter both Email and Password", Toast.LENGTH_SHORT)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(RaisinBlack, DarkOnyx)))
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Onyx)
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
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("Enter Email") },
                textStyle = TextStyle(color = Silver),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                leadingIcon = {
                    Icon(
                        Icons.Filled.Email,
                        contentDescription = "Email",
                        tint = Silver,
                        modifier = Modifier.size(18.dp) // Bigger icon
                    )
                },
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password Input
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                placeholder = { Text("Enter Password") },
                textStyle = TextStyle(color = Silver),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                leadingIcon = {
                    Icon(
                        Icons.Filled.Lock,
                        contentDescription = "Password",
                        tint = Silver,
                        modifier = Modifier.size(18.dp) // Bigger icon
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle Password Visibility",
                            tint = Silver,
                            modifier = Modifier.size(18.dp) // Bigger icon
                        )
                    }
                },
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Login Button
            Button(
                onClick = {
                    when {
                        !Validations.isValidEmail(email) && !Validations.isValidPassword(password) -> bothToast.show()
                        !Validations.isValidEmail(email) -> emailToast.show()
                        !Validations.isValidPassword(password) -> passToast.show()
                        else -> navController.navigate("home")
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Silver),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Login", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = RaisinBlack)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Filled.ArrowForward,
                    contentDescription = "Login",
                    tint = RaisinBlack,
                    modifier = Modifier.size(18.dp) // Bigger icon
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot Password
            TextButton(onClick = { /* Forgot Password Action */ }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        Icons.Filled.HelpOutline,
//                        contentDescription = "Help",
//                        tint = CadetGray,
//                        modifier = Modifier.size(18.dp) // Bigger icon
//                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Forgot Password?", color = CadetGray, fontSize = 14.sp)
                }
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { navController.navigate("register") }
                ) {
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        "Register",
                        color = Silver,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        Icons.Filled.AddReaction,
                        contentDescription = "Register",
                        tint = Silver,
                        modifier = Modifier.size(18.dp) // Bigger icon
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLogin() {
    LoginScreen(navController = rememberNavController())
}
