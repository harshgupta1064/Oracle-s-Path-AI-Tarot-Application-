package com.example.aitarotapplication.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aitarotapplication.R
import com.example.aitarotapplication.adapters.ChatAdapter
import com.example.aitarotapplication.databinding.ActivityChatBinding
import com.example.aitarotapplication.models.ChatMessage
import com.example.aitarotapplication.network.GeminiHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatActivity : AppCompatActivity() {
    val binding : ActivityChatBinding by lazy {
        ActivityChatBinding.inflate(layoutInflater)
    }
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSend: ImageButton
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var userQuestion: String
    private lateinit var card1Message:String
    private lateinit var card2Message:String
    private lateinit var card3Message:String
    private val messages = mutableListOf<ChatMessage>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        chatRecyclerView = binding.recyclerViewChat
        editTextMessage = binding.editTextMessage
        buttonSend = binding.buttonSend
        userQuestion = intent.getStringExtra("userQuestion").toString()
        card1Message = intent.getStringExtra("card1Message").toString()
        card2Message = intent.getStringExtra("card2Message").toString()
        card3Message = intent.getStringExtra("card3Message").toString()

        chatAdapter = ChatAdapter(messages)
        chatRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = chatAdapter
        }
        buttonSend.setOnClickListener {
            val currentMessage = editTextMessage.text.toString().trim()

            if (currentMessage.isNotEmpty()) {
                // Add the user's current message to the RecyclerView
                messages.add(ChatMessage(currentMessage, isUser = true))
                chatAdapter.notifyItemInserted(messages.size - 1)
                chatRecyclerView.scrollToPosition(messages.size - 1)

                // Clear the input field
                editTextMessage.text.clear()

                // Send userQuestion (context) + last 3 messages + current message to Gemini
                getGeminiResponse(userQuestion, currentMessage)
            }
        }

    }

    private fun getGeminiResponse(userQuestion: String, currentMessage: String) {
        val geminiHelper = GeminiHelper()

        // Get the last 3 messages (if available) to provide chat context
        val lastMessages = messages.takeLast(3).joinToString("\n") { it.message }

        // Create the input prompt for Gemini (context + previous messages + new message)
        val inputPrompt = """
        Context: $userQuestion
        Previous Messages:
        $lastMessages
        User: $currentMessage
        AI:
    """.trimIndent()

        CoroutineScope(Dispatchers.IO).launch {
            val response = geminiHelper.getChatResponse(inputPrompt) // Send formatted input

            withContext(Dispatchers.Main) {
                // Add AI response to RecyclerView
                messages.add(ChatMessage(response, isUser = false))
                chatAdapter.notifyItemInserted(messages.size - 1)
                chatRecyclerView.scrollToPosition(messages.size - 1)
            }
        }
    }


}