import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aitarotapplication.data.Reading
@Dao
interface ReadingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReading(reading: Reading)

    @Query("SELECT * FROM readings ORDER BY readingDate DESC")
    fun getAllReadings(): LiveData<List<Reading>>
}
