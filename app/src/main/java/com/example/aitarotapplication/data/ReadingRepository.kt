
package com.example.aitarotapplication.data

import ReadingDao
import androidx.lifecycle.LiveData

class ReadingRepository(private val readingDao: ReadingDao) {

    val allReadings: LiveData<List<Reading>> = readingDao.getAllReadings()

    suspend fun insert(reading: Reading) {
        readingDao.insertReading(reading)
    }
}
