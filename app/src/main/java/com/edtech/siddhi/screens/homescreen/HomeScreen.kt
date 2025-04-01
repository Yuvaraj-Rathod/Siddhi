package com.edtech.siddhi.screens.homescreen

import CodingPlatformSection
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AssistWalker
import androidx.compose.material.icons.filled.Assistant
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.edtech.siddhi.ui.theme.*
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.edtech.siddhi.viewmodel.LeetcodeViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.swiperefresh.SwipeRefresh
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: LeetcodeViewModel = hiltViewModel()
    val user by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getUser("priyanshu2202k")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("bot") },
                containerColor = Color.DarkGray, // Darker Silver for better contrast
                shape = CircleShape,
                modifier = Modifier.size(63.dp)
            ) {
                Icon(imageVector = Icons.Filled.CatchingPokemon, contentDescription = "Add", tint = Color.Cyan, modifier = Modifier.size(43.dp))
            }
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 6.dp, vertical = 6.dp), // Reduced padding
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileSection(user, navController)

            Box(modifier = Modifier.fillMaxWidth().height(220.dp)) { // Reduced height
                LeetCodeProfileSection(
                    viewModel = hiltViewModel(),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            CodingPlatformSection(
                modifier = Modifier.fillMaxWidth()
            )


            SubjectSection(
                navController = navController,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
