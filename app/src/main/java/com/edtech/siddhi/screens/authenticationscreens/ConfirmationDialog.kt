package com.edtech.siddhi.screens.authenticationscreens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.edtech.siddhi.ui.theme.*
import com.edtech.siddhi.viewmodel.AuthViewModel

@Composable
fun ConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Confirm Sign Out", color = Color.White)
        },
        text = {
            Text("Are you sure you want to sign out?", color = Color.LightGray)
        },
        containerColor = Color(0xFF1E1E1E),
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    "Sign Out",
                    color = RaisinBlack,
                    modifier = Modifier
                        .background(SoftCaramel, RoundedCornerShape(10.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = SoftCaramel)
            }
        }
    )
}

