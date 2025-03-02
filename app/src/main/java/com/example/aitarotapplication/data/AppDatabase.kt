package com.example.aitarotapplication.data
import com.example.aitarotapplication.data.ReadingDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Reading::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun readingDao(): ReadingDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tarot_history_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
