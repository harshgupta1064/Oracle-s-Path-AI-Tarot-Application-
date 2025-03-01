package com.example.aitarotapplication.network

import com.google.ai.client.generativeai.GenerativeModel

class GeminiHelper {

    private val apiKey = "AIzaSyCBtVPLoEQ5mzJ0Sgterx4aETDvNQojb3I"

    private val model = GenerativeModel(
        modelName = "gemini-2.0-flash",
        apiKey = apiKey


    )

    suspend fun getChatResponse(prompt: String): String {
        return try {
            val response = model.generateContent(prompt)
            response.text ?: "No response from AI"
        } catch (e: Exception) {
            e.printStackTrace()
            "Error: ${e.message}"
        }
    }
}