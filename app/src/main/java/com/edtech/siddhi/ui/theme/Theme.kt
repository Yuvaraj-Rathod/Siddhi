package com.edtech.siddhi.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val SiddhiLightColorScheme = lightColorScheme(
    primary = DimGray,       // Main UI elements
    onPrimary = Color.White, // White text/icons on primary elements
    secondary = Onyx,        // Secondary elements like buttons
    onSecondary = Color.White, // White text/icons on secondary elements
    background = RaisinBlack, // Background color for the entire screen
    onBackground = Silver,   // Light gray text/icons on dark background
    surface = CadetGray,     // Used for cards, input fields
    onSurface = Color.White  // White text/icons on surfaces
)
@Composable
fun SiddhiTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    val systemUiController = rememberSystemUiController()

    if (!view.isInEditMode) {
        SideEffect {
            systemUiController.isStatusBarVisible = true
            systemUiController.isNavigationBarVisible = true
            systemUiController.setStatusBarColor(Color.Transparent, darkIcons = false)
            systemUiController.setNavigationBarColor(Color.Transparent, darkIcons = false)
        }
    }

    MaterialTheme(
        colorScheme = SiddhiLightColorScheme,
        typography = Typography
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(RaisinBlack) // Solid background color
        ) {
            content() // Your main UI elements
        }
    }
}
