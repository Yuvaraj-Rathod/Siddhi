import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edtech.siddhi.model.CodingPlatform
import androidx.core.net.toUri

@Composable
fun CodingPlatformSection(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val platforms = listOf(
        CodingPlatform("LeetCode", "Top 150 Must Do Questions",
            Brush.verticalGradient(listOf(Color(0xFFFFD54F), Color(0xFFFFA000))),
            "https://leetcode.com/studyplan/top-interview-150/"),
        CodingPlatform("GeeksForGeeks", "Best 75 Coding Questions",
            Brush.verticalGradient(listOf(Color(0xFF81C784), Color(0xFF388E3C))),
            "https://www.geeksforgeeks.org/blind-75/"),
        CodingPlatform("TUF", "TCS NQT Must Do 100s",
            Brush.verticalGradient(listOf(Color(0xFF64B5F6), Color(0xFF1976D2))),
            "https://takeuforward.org/interviews/tcs-nqt-coding-sheet-tcs-coding-questions/"),
        CodingPlatform("LeetCode SQL", "Top 50 SQL Interview Questions",
            Brush.verticalGradient(listOf(Color(0xFFBA68C8), Color(0xFF6A1B9A))),
            "https://leetcode.com/studyplan/top-sql-50/")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(5.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(platforms) { platform ->
            Card(
                shape = RoundedCornerShape(16.dp), // Subtle rounded corners
                elevation = CardDefaults.cardElevation(6.dp), // Elevation for shadow
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.1f) // Slightly adjusted for better alignment
                    .clickable {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(platform.url)))
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(platform.gradient)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = platform.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = platform.description,
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    CodingPlatformSection()
}