package com.edtech.siddhi.screens.subject

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edtech.siddhi.model.YoutubeVideo
import com.edtech.siddhi.model.DifficultyTag
import androidx.core.net.toUri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.edtech.siddhi.R
import kotlinx.coroutines.delay

@Composable
fun YoutubeVideoCard(
    video: YoutubeVideo,
    context: Context,
    thumbnailResId: Int,
    onFavoriteToggle: (Boolean) -> Unit,
    onWatchedToggle: (Boolean) -> Unit
) {
    var isFavorite by remember { mutableStateOf(video.isFavorite) }
    var isWatched by remember { mutableStateOf(video.isWatched) }

    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(horizontal = 6.dp, vertical = 6.dp)
            .clickable { openYoutubeVideo(context, video.link) },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Thumbnail with Difficulty Tag and Like button inside a single column
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            ) {
                // Thumbnail Image
                Image(
                    painter = painterResource(id = thumbnailResId),
                    contentDescription = "Thumbnail",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                    contentScale = ContentScale.Crop
                )

                // Column for Difficulty Tag and Like button
                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 6.dp, end = 6.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    // Difficulty Tag
                    DifficultyTag(video.difficultyTag)

                    Spacer(modifier = Modifier.height(40.dp))

                    // Like button positioned below the Difficulty Tag
                    IconButton(
                        onClick = {
                            isFavorite = !isFavorite
                            onFavoriteToggle(isFavorite)
                        },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color.Red else Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            // Content below thumbnail
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                // Title with Marquee effect for overflow text
                MarqueeText(text = video.title)

                // Row for Channel Name and Watched Icon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Channel Name
                    Text(
                        text = video.channelName,
                        fontSize = 10.sp,
                        color = Color.LightGray
                    )

                    // Watched Icon
                    IconButton(
                        onClick = {
                            isWatched = !isWatched
                            onWatchedToggle(isWatched)
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = if (isWatched) Icons.Filled.Check else Icons.Outlined.VisibilityOff,
                            contentDescription = "Watched",
                            tint = if (isWatched) Color.Green else Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}





@Composable
fun DifficultyTag(difficulty: DifficultyTag, modifier: Modifier = Modifier) {
    val backgroundColor = when (difficulty) {
        DifficultyTag.EASY -> Color(0xFF4CAF50) // Green
        DifficultyTag.MEDIUM -> Color(0xFFFFC107) // Yellow
        DifficultyTag.HARD -> Color(0xFFF44336) // Red
    }

    Box(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(4.dp)) // Smaller radius
            .padding(horizontal = 4.dp, vertical = 2.dp) // Reduced padding
    ) {
        Text(
            text = difficulty.name,
            color = Color.Black,
            fontSize = 9.sp, // Smaller font size
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun MarqueeText(text: String, modifier: Modifier = Modifier) {
    var scrollState = rememberScrollState(0)

    LaunchedEffect(Unit) {
        while (true) {
            delay(100) // Smooth scroll delay
            val newScroll = (scrollState.value + 2).coerceAtMost(scrollState.maxValue)
            scrollState.scrollTo(newScroll)

            if (newScroll == scrollState.maxValue) {
                delay(500) // Pause at end before resetting
                scrollState.scrollTo(0)
            }
        }
    }

    Box(modifier = modifier) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.horizontalScroll(scrollState)
        )
    }
}




fun openYoutubeVideo(context: Context, videoUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW, videoUrl.toUri())
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun PreviewYoutubeVideoCard() {
    val context = LocalContext.current
    YoutubeVideoCard(
        video = YoutubeVideo(
            title = "Introduction to DBMS",
            link = "https://www.youtube.com/watch?v=xyz",
            channelName = "Tech Channel",
            difficultyTag = DifficultyTag.MEDIUM,
            isFavorite = false,
            isWatched = false
        ),
        context = context,
        thumbnailResId = R.drawable.dbms, // Replace with an actual drawable
        onFavoriteToggle = {},
        onWatchedToggle = {}
    )
}