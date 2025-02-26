package com.example.aitarotapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.aitarotapplication.data.AppDatabase
import com.example.aitarotapplication.data.Reading
import com.example.aitarotapplication.data.ReadingRepository

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ReadingRepository
    val allReadings: LiveData<List<Reading>>

    init {
        val readingDao = AppDatabase.getDatabase(application).readingDao()
        repository = ReadingRepository(readingDao)
        allReadings = repository.allReadings
    }
}
