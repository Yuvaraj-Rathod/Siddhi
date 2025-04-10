package com.edtech.siddhi.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp

@Composable
fun QuickEdgePanel(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val haptics = LocalHapticFeedback.current

    val width by animateDpAsState(
        targetValue = if (expanded) 64.dp else 13.dp,
        label = "width"
    )

    val height by animateDpAsState(
        targetValue = if (expanded) Dp.Unspecified else 80.dp,
        label = "height"
    )

    val panelShape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)

    Box(
        modifier = modifier
            .then(
                if (!expanded)
                    Modifier.height(height)
                else
                    Modifier.fillMaxHeight()
            )
            .width(width)
            .offset(x = (-6).dp)
            .clip(panelShape)
            .background(Color(0x66121212))
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount < -10 && !expanded) {
                        expanded = true
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    } else if (dragAmount > 10 && expanded) {
                        expanded = false
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    }
                }
            }
            .clickable {
                expanded = !expanded
                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
            }
    ) {
        if (!expanded) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color(0x66121212))
            )
        }

        if (expanded) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 100.dp, horizontal = 4.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = {
                    navController.navigate("home")
                    expanded = false
                }) {
                    Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White)
                }
                IconButton(onClick = {
                    navController.navigate("cn")
                    expanded = false
                }) {
                    Icon(Icons.Default.Archive, contentDescription = "Archive", tint = Color.White)
                }
                IconButton(onClick = {
                    navController.navigate("bot")
                    expanded = false
                }) {
                    Icon(Icons.Default.Person, contentDescription = "chatbot", tint = Color.White)
                }
                IconButton(onClick = {
                    navController.navigate("home")
                    expanded = false
                }) {
                    Icon(Icons.Default.Refresh, contentDescription = "refresh", tint = Color.White)
                }
            }
        }
    }
}





