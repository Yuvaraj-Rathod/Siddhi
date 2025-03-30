package com.edtech.siddhi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.edtech.siddhi.navigation.AppNavigation
import com.edtech.siddhi.ui.theme.SiddhiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge drawing for system bars
        setContent {
            SiddhiTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = androidx.compose.ui.graphics.Color.Transparent, // Make it transparent to show gradient
                    contentWindowInsets = ScaffoldDefaults.contentWindowInsets // Handle system insets
                ) { innerPadding ->
                   AppNavigation( modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
