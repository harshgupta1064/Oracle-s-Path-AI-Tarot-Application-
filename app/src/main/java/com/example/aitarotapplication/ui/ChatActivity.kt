package com.example.aitarotapplication.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.Manifest
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aitarotapplication.databinding.ActivityChatBinding
import com.example.aitarotapplication.adapters.ChatAdapter
import com.example.aitarotapplication.models.ChatMessage
import com.example.aitarotapplication.network.GeminiHelper
import kotlinx.coroutines.*
import java.util.Locale

class ChatActivity : AppCompatActivity() {
    private val binding: ActivityChatBinding by lazy { ActivityChatBinding.inflate(layoutInflater) }
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var tts: TextToSpeech
    private var isVoiceMode = false
    private lateinit var userQuestion: String
    private val messages = mutableListOf<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        checkMicrophonePermission()
        initializeUI()
        initializeSpeechRecognizer()
        initializeTextToSpeech()
    }

    private fun checkMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        }
    }

    private fun initializeUI() {
        userQuestion = intent.getStringExtra("userQuestion").toString()
        chatAdapter = ChatAdapter(messages)

        binding.recyclerViewChat.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = chatAdapter
        }

        binding.buttonSend.setOnClickListener {
            val currentMessage = binding.editTextMessage.text.toString().trim()
            if (currentMessage.isNotEmpty()) {
                processUserInput(currentMessage)
                binding.editTextMessage.text.clear()
            }
        }

        binding.micButton.setOnClickListener {
            isVoiceMode = true
            toggleVoiceUI(true)
            startListening()
        }

        binding.micOffButton.setOnClickListener {
            isVoiceMode = false
            toggleVoiceUI(false)
            stopListening()
            tts.stop()
        }
    }

    private fun initializeSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {
                handleSpeechError(error)
                if (isVoiceMode) startListening()
            }
            override fun onResults(results: Bundle?) {
                results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull()?.let {
                    processUserInput(it)
                }
            }
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    private fun initializeTextToSpeech() {
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) tts.language = Locale.US
        }
    }

    private fun handleSpeechError(error: Int) {
        val errorMessage = when (error) {
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
            SpeechRecognizer.ERROR_NETWORK -> "Network error"
            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
            SpeechRecognizer.ERROR_SERVER -> "Server error"
            SpeechRecognizer.ERROR_CLIENT -> "Client error"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
            SpeechRecognizer.ERROR_NO_MATCH -> "No matching speech found"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RecognitionService is busy"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
            else -> "Unknown error"
        }
        Toast.makeText(this, "Speech Error: $errorMessage", Toast.LENGTH_SHORT).show()
    }

    private fun toggleVoiceUI(enable: Boolean) {
        binding.recyclerViewChat.visibility = if (enable) View.GONE else View.VISIBLE
        binding.editTextMessage.visibility = if (enable) View.GONE else View.VISIBLE
        binding.buttonSend.visibility = if (enable) View.GONE else View.VISIBLE
        binding.micButton.visibility = if (enable) View.GONE else View.VISIBLE
        binding.micOffButton.visibility = if (enable) View.VISIBLE else View.GONE
    }

    private fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Listening...")
        }
        speechRecognizer.startListening(intent)
    }

    private fun stopListening() {
        try {
            speechRecognizer.cancel()
            speechRecognizer.destroy()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun processUserInput(userInput: String) {
        messages.add(ChatMessage(userInput, isUser = true))
        chatAdapter.notifyItemInserted(messages.size - 1)
        binding.recyclerViewChat.scrollToPosition(messages.size - 1)
        getGeminiResponse(userQuestion, userInput)
    }

    private fun getGeminiResponse(userQuestion: String, currentMessage: String) {
        val geminiHelper = GeminiHelper()
        val lastMessages = messages.takeLast(3).joinToString("\n") { it.message }
        val inputPrompt = """
            Context: $userQuestion
            Previous Messages:
            $lastMessages
            User: $currentMessage
            AI:
        """.trimIndent()

        CoroutineScope(Dispatchers.IO).launch {
            val response = geminiHelper.getChatResponse(inputPrompt)
            withContext(Dispatchers.Main) {
                messages.add(ChatMessage(response, isUser = false))
                chatAdapter.notifyItemInserted(messages.size - 1)
                binding.recyclerViewChat.scrollToPosition(messages.size - 1)
                speakResponse(response)
            }
        }
    }

    private fun speakResponse(response: String) {
        tts.speak(response, TextToSpeech.QUEUE_FLUSH, null, "TTS_ID")
        tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String) {}
            override fun onDone(utteranceId: String) { runOnUiThread { startListening() } }
            override fun onError(utteranceId: String) {}
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
        tts.shutdown()
    }
}
