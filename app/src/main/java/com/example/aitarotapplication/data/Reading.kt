package com.example.aitarotapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "readings")
data class Reading(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userQuestion: String,
    val card1Message: String,
    val card2Message: String,
    val card3Message: String,
    val readingDate: Long // store timestamp (e.g., System.currentTimeMillis())
):Serializable
