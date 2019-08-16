package moe.misakachan.runway.Repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import moe.misakachan.runway.Database.AlarmDatabase
import moe.misakachan.runway.Models.Alarm
import java.lang.Exception

class AlarmRepository (application: Application)
{
    private val alarmDatabase = AlarmDatabase.getInstance(application)
    private val alarmDAO = alarmDatabase.alarmDAO()
    private val alarms: LiveData<List<Alarm>> = alarmDAO.getAll()

    fun getAll() : LiveData<List<Alarm>>{
        return alarms
    }

    fun insert(alarm: Alarm)
    {
        try {
            Thread(Runnable {
                alarmDAO.insert(alarm)
            }).start()
        } catch (e: Exception) {
            Log.d("MisakaMOE",e.message!!)
        }
    }

    fun delete(alarm: Alarm)
    {
        try {
            Thread(Runnable {
                alarmDAO.delete(alarm)
            }).start()
        } catch (e:Exception){
            Log.d("MisakaMOE",e.message!!)
        }
    }
}