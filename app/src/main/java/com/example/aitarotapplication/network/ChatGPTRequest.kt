// File: app/src/main/java/com/example/aitarotapplication/network/ChatGPTRequest.kt
package com.example.aitarotapplication.network

data class ChatGPTMessage(
    val role: String,
    val content: String
)

data class ChatGPTRequest(
    val model: String = "gpt-3.5-turbo",  // or another model as needed
    val messages: List<ChatGPTMessage>
)

