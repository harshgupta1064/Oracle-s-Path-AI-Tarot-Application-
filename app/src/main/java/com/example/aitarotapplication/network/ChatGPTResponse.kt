package com.example.aitarotapplication.network

data class ChatGPTChoice(
    val message: ChatGPTMessage
    // You can add fields like finish_reason, index, etc.
)

data class ChatGPTResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val choices: List<ChatGPTChoice>
)
