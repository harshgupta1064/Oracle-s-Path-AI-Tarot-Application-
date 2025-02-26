package com.example.aitarotapplication.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aitarotapplication.adapters.HistoryAdapter
import com.example.aitarotapplication.databinding.ActivityHistoryListBinding
import com.example.aitarotapplication.viewmodels.HistoryViewModel

class HistoryList : AppCompatActivity() {
    val binding:ActivityHistoryListBinding by lazy {
        ActivityHistoryListBinding.inflate(layoutInflater)
    }
    private lateinit var recyclerView: RecyclerView
    private val historyViewModel: HistoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe changes in the reading history LiveData.
        historyViewModel.allReadings.observe(this, Observer { readings ->
            // Set the adapter when data is available.
            recyclerView.adapter = HistoryAdapter(readings)
        })

    }
}