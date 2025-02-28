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
import com.example.aitarotapplication.network.ChatGPTMessage
import com.example.aitarotapplication.network.ChatGPTRequest
import com.example.aitarotapplication.network.RetrofitClient
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity() {
    val binding : ActivityChatBinding by lazy {
        ActivityChatBinding.inflate(layoutInflater)
    }
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSend: ImageButton
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var userQuestion: String
    private val messages = mutableListOf<ChatMessage>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        chatRecyclerView = binding.recyclerViewChat
        editTextMessage = binding.editTextMessage
        buttonSend = binding.buttonSend
        userQuestion = intent.getStringExtra("userQuestion").toString()

        chatAdapter = ChatAdapter(messages)
        chatRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = chatAdapter
        }

        buttonSend.setOnClickListener {
            val currentMessage = editTextMessage.text.toString().trim()
            if (currentMessage.isNotEmpty()) {
                // Add the user's current message
                messages.add(ChatMessage(currentMessage, isUser = true))
                chatAdapter.notifyItemInserted(messages.size - 1)
                chatRecyclerView.scrollToPosition(messages.size - 1)
                editTextMessage.text.clear()
                
            }
        }

    }


}