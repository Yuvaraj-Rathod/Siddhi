package com.edtech.siddhi.screens.homescreen


import CodingPlatformSection
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.edtech.siddhi.api.UserDetail
import com.edtech.siddhi.screens.QuickEdgePanel
import com.edtech.siddhi.screens.authenticationscreens.ConfirmationDialog
import com.edtech.siddhi.skeletalloading.CodingPlatformSkeleton
import com.edtech.siddhi.skeletalloading.LeetCodeProfileSkeleton
import com.edtech.siddhi.skeletalloading.ProfileSectionSkeleton
import com.edtech.siddhi.skeletalloading.SubjectSkeleton
import com.edtech.siddhi.viewmodel.AuthState
import com.edtech.siddhi.viewmodel.AuthViewModel
import com.edtech.siddhi.viewmodel.LeetcodeViewModel
import com.edtech.siddhi.viewmodel.UserFireStoreViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.firebase.auth.oAuthProvider
import com.google.rpc.context.AttributeContext.Auth
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(navController: NavController, authViewModel: AuthViewModel) {
    val leetcodeViewModel: LeetcodeViewModel = hiltViewModel()
    val userViewModel: UserFireStoreViewModel = viewModel()

    val user by leetcodeViewModel.user.collectAsState()
    val profile by leetcodeViewModel.profile.collectAsState()
    val userDetailFireStore by userViewModel.userDetails.observeAsState()

    val isLoading = user == null || profile == null

    LaunchedEffect(userDetailFireStore) {
        userDetailFireStore?.let {
            leetcodeViewModel.getUser(it.leetcodeId)
            leetcodeViewModel.getProfile(it.leetcodeId)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("bot") },
                containerColor = Color(0xFF434344),
                shape = CircleShape,
                modifier = Modifier.size(60.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "person",
                    tint = Color(0xFFE3A869),
                    modifier = Modifier.size(30.dp)
                )
            }
        }
//        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFF121212))) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 6.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLoading) ProfileSectionSkeleton() else ProfileSection(user, navController, authViewModel)
                if (isLoading) LeetCodeProfileSkeleton() else userDetailFireStore?.let { LeetCodeProfileSection(leetcodeViewModel, it) }
                if (isLoading) CodingPlatformSkeleton() else CodingPlatformSection(Modifier.fillMaxWidth())
                if (isLoading) SubjectSkeleton() else SubjectSection(navController = navController, modifier = Modifier.fillMaxWidth())
            }

            QuickEdgePanel(
                navController = navController,
                modifier = Modifier.align(Alignment.CenterEnd) .offset(y = (-10).dp)
            )

        }
    }
}



@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), authViewModel = AuthViewModel())
}
