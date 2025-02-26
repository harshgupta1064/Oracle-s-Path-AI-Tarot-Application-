package com.example.aitarotapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
// other imports

@Dao
interface ReadingDao {
    @Insert
    suspend fun insertReading(reading: Reading)

    @Query("SELECT * FROM readings ORDER BY readingDate DESC")
    fun getAllReadings(): LiveData<List<Reading>>
}
