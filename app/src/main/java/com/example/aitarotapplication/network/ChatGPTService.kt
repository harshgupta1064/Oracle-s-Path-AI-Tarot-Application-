// File: app/src/main/java/com/example/aitarotapplication/network/ChatGPTService.kt
package com.example.aitarotapplication.network

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatGPTService {
    @Headers("Content-Type: application/json")
    @POST("chat/completions")
    suspend fun getChatCompletion(@Body request: ChatGPTRequest): ChatGPTResponse
}
