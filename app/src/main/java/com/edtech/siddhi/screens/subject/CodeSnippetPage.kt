package com.edtech.siddhi.screens.subject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.edtech.siddhi.R
import com.edtech.siddhi.ui.theme.RaisinBlack
import com.edtech.siddhi.viewmodel.youtubeviewmodels.CodeSnippetViewModel

@Composable
fun CodeSnippetPage(modifier: Modifier=Modifier ,navController: NavController){
    val codeSnippetViewModel : CodeSnippetViewModel = hiltViewModel()
    val codesnippetplaylist by codeSnippetViewModel.codesnippetplaylist.observeAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("home")},
                containerColor = Color.Cyan,
                shape = RoundedCornerShape(30),
                modifier = Modifier.padding(bottom = 15.dp)
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back", tint = RaisinBlack)
            }
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)  // Ensure padding is applied here
                    .background(Color(0xFF121212))  // Set the background color of the content
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // Two columns
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(codesnippetplaylist) { video ->
                        YoutubeVideoCard(
                            video = video,
                            context = LocalContext.current,
                            thumbnailResId = R.drawable.csnippet,
                            onFavoriteToggle = { /* dbmsViewModel.toggleFavorite(video) */ },
                            onWatchedToggle = { /* dbmsViewModel.onWatchedToggle(video) */ }
                        )
                    }
                }
            }
        }
    )
}