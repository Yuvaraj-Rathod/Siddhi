package com.edtech.siddhi.screens.chatbotscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.edtech.siddhi.model.MessageModel
import com.edtech.siddhi.viewmodel.ChatViewModel
import com.edtech.siddhi.ui.theme.DarkOnyx
import com.edtech.siddhi.ui.theme.Silver
import com.edtech.siddhi.ui.theme.CadetGray

@Composable
fun ChatScreen(modifier: Modifier = Modifier, chatViewModel: ChatViewModel) {
    Scaffold(
        bottomBar = { MessageInput { chatViewModel.sendMessage(it) } },
        containerColor = Color(0xFF1E1E2E) // Dark modern background
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                MessageList(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f),
                    messageList = chatViewModel.messageList
                )

                if (chatViewModel.messageList.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LottieAnimationPlaceholder()
                    }
                }
            }
        }
    }
}

@Composable
fun LottieAnimationPlaceholder() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("chatloading.json"))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(160.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInput(onMessageSend: (String) -> Unit) {
    var message by remember { mutableStateOf("") }

    Surface(
        color = Color(0xFF2B2D42),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = message,
                onValueChange = { message = it },
                placeholder = { Text("Ask me anything...", color = CadetGray) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF232539),
                    unfocusedContainerColor = Color(0xFF232539),
                    focusedTextColor = Silver,
                    unfocusedTextColor = CadetGray
                ),
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(10.dp))

            IconButton(
                onClick = {
                    if (message.isNotEmpty()) {
                        onMessageSend(message)
                        message = ""
                    }
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF0077B6))
                    .padding(12.dp)
                    .size(22.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    tint = Color.White,
                    modifier = Modifier.size(17.dp)
                )
            }
        }
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<MessageModel>) {
    LazyColumn(modifier = modifier, reverseLayout = true) {
        items(messageList.reversed()) {
            MessageRow(messagemodel = it)
        }
    }
}

@Composable
fun MessageRow(messagemodel: MessageModel) {
    val isModel = messagemodel.role == "model"
    val messageBackground = if (isModel) Color(0xFF3A3F58) else Color(0xFF0077B6)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = if (isModel) Arrangement.Start else Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(messageBackground)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .widthIn(min = 100.dp, max = 300.dp)
        ) {
            SelectionContainer {
                Text(
                    text = messagemodel.message,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    }
}