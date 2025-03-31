package com.edtech.siddhi.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edtech.siddhi.model.MessageModel
import com.edtech.siddhi.utils.Constants
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch


class ChatViewModel : ViewModel() {

    val generativeModel = GenerativeModel(modelName = "gemini-1.5-flash", apiKey = Constants.apiKey)
    val messageList = mutableStateListOf<MessageModel>()

    fun sendMessage(question: String) {
        viewModelScope.launch {
            try {
                // Start the chat
                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role) {
                            text(it.message) }
                    }.toList()
                )

                // Put the user message in history list
                messageList.add(MessageModel(question, "user"))
                messageList.add(MessageModel("Typing...", "model"))

                // Send the message and get the response
                val response = chat.sendMessage(question)
                messageList.removeAt(messageList.lastIndex)// Ensure the list is not empty
                // Add response to history list
                messageList.add(MessageModel(response.text.toString(), "model"))

            } catch (e: Exception) {
                messageList.removeAt(messageList.lastIndex) // Clean up the "Typing..." message
                    messageList.add(MessageModel("Error: ${e.message}", "model"))
            }
        }
    }
}

