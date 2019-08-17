package moe.misakachan.runway.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import moe.misakachan.runway.Models.Alarm

@Database(entities = [Alarm::class], version = 1)
abstract class AlarmDatabase: RoomDatabase()
{
    abstract fun alarmDAO() : AlarmDAO

    //for SingleTon
    companion object{
        private var Instance: AlarmDatabase? = null
        fun getInstance(context: Context): AlarmDatabase{
            if(Instance==null)
            {
                synchronized(AlarmDatabase::class)
                {
                    Instance = Room.databaseBuilder(context.applicationContext, AlarmDatabase::class.java, "alarm").fallbackToDestructiveMigration().build()
                }
            }
            return this.Instance!!
        }
    }
}