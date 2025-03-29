package com.edtech.siddhi.screens

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.edtech.siddhi.ui.theme.*
import com.edtech.siddhi.utils.Validations

@Composable
fun RegistrationScreen(modifier: Modifier = Modifier, navController: NavController) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var leetCodeLink by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var leetCodeError by remember { mutableStateOf(false) }

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
                .background(Color(0xFF434344))
                .padding(24.dp)
        ) {
            Text(
                text = "Create an Account",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Silver
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Sign up to continue",
                style = MaterialTheme.typography.bodyMedium,
                color = CadetGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Name Input Field
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name", color = Silver) },
                textStyle = TextStyle(color = Color.White),
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "Name Icon", tint = Silver, modifier = Modifier.size(18.dp))
                },
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email Input Field
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = !Validations.isValidEmail(email)
                },
                label = { Text("Email", color = Silver) },
                textStyle = TextStyle(color = Color.White),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Email Icon", tint = Silver, modifier = Modifier.size(18.dp))
                },
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth(),
                isError = emailError
            )
            if (emailError) Text("Invalid Email", color = Color.Red, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(12.dp))

            // Password Input Field
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = !Validations.isValidPassword(password)
                },
                label = { Text("Password", color = Silver) },
                textStyle = TextStyle(color = Color.White),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Password Icon", tint = Silver, modifier = Modifier.size(18.dp))
                },
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth(),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                isError = passwordError
            )
            if (passwordError) Text("Password must be at least 6 characters, include a number, letter, and symbol.", color = Color.Red, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(12.dp))

            // LeetCode Profile Link Input Field
            OutlinedTextField(
                value = leetCodeLink,
                onValueChange = {
                    leetCodeLink = it
                    leetCodeError = !Validations.isValidLeetCodeProfile(leetCodeLink)
                },
                label = { Text("LeetCode Profile Link", color = Silver) },
                textStyle = TextStyle(color = Color.White),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),
                leadingIcon = {
                    Icon(Icons.Default.Link, contentDescription = "LeetCode Icon", tint = Silver, modifier = Modifier.size(18.dp))
                },
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth(),
                isError = leetCodeError
            )
            if (leetCodeError) Text("Enter a valid LeetCode profile link", color = Color.Red, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(20.dp))

            // Register Button
            Button(
                onClick = {
                    if (emailError || passwordError || leetCodeError) {
                        Toast.makeText(context, "Please fix the errors", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Registration Successful!", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Silver),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Register", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Already have an account? Login
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Already have an account?", color = CadetGray, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Login",
                    color = Silver,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { navController.navigate("login") }
                )
            }
        }
    }
}

// Common text field styling
@Composable
fun textFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    errorContainerColor = Color.Transparent,
    focusedIndicatorColor = Silver,
    unfocusedIndicatorColor = CadetGray,
    disabledIndicatorColor = Color.Gray,
    errorIndicatorColor = Color.Red,
    cursorColor = Silver,
    errorCursorColor = Color.Red,
    focusedLabelColor = Silver,
    unfocusedLabelColor = CadetGray,
    errorLabelColor = Color.Red,
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    disabledTextColor = Color.Gray,
    errorTextColor = Color.Red
)

@Preview
@Composable
private fun PreviewRegistration() {
    RegistrationScreen(navController = rememberNavController())
}
