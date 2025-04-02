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
import com.edtech.siddhi.skeletalloading.CodingPlatformSkeleton
import com.edtech.siddhi.skeletalloading.LeetCodeProfileSkeleton
import com.edtech.siddhi.skeletalloading.ProfileSectionSkeleton
import com.edtech.siddhi.skeletalloading.SubjectSkeleton
import com.edtech.siddhi.viewmodel.LeetcodeViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.swiperefresh.SwipeRefresh
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: LeetcodeViewModel = hiltViewModel()
    val user by viewModel.user.collectAsState()
    val profile by viewModel.profile.collectAsState()

    val isLoading = user == null || profile == null

    LaunchedEffect(Unit) {
        viewModel.getUser("Ankush3323")
        viewModel.getProfile("Ankush3323")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("bot") },
                containerColor = Color.DarkGray,
                shape = CircleShape,
                modifier = Modifier.size(63.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.CatchingPokemon,
                    contentDescription = "Add",
                    tint = Color.Cyan,
                    modifier = Modifier.size(43.dp)
                )
            }
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 6.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Section
            if (isLoading) ProfileSectionSkeleton() else ProfileSection(user, navController)

            // LeetCode Profile Section
            Box(modifier = Modifier.fillMaxWidth().height(220.dp)) {
                if (isLoading) LeetCodeProfileSkeleton() else LeetCodeProfileSection(viewModel)
            }

            // Coding Platforms Section
            if (isLoading) CodingPlatformSkeleton() else CodingPlatformSection(Modifier.fillMaxWidth())


            // Subjects Section
            if (isLoading) SubjectSkeleton() else SubjectSection(                               //CORE TOPICS
                navController = navController,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
