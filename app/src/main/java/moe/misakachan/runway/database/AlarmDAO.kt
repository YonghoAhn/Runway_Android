package moe.misakachan.runway.database

import androidx.lifecycle.LiveData
import androidx.room.*
import moe.misakachan.runway.models.Alarm

@Dao
interface AlarmDAO{
    @Query("SELECT * FROM alarm ORDER BY id ASC")
    fun getAll() : LiveData<List<Alarm>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(alarm: Alarm)

    @Delete
    fun delete(alarm: Alarm)

}