package com.example.aitarotapplication.models

data class ChatMessage(
    val message: String,
    val isUser: Boolean  // false for AI messages, true for user messages
)
