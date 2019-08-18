package moe.misakachan.runway.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import moe.misakachan.runway.models.Alarm
import moe.misakachan.runway.repositories.AlarmRepository

class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AlarmRepository(application)
    private val alarms = repository.getAll()

    private val alarmsList = alarms.value

    private val currentAlarm = alarmsList?.get(0)

    /*
        Mutable Observable (origin data)
     */

    //TODO: add fields

    /*
      Observable Fields for out
    */


    fun getAll() : LiveData<List<Alarm>>
    {
        return this.alarms
    }

    fun insert(alarm: Alarm)
    {
        repository.insert(alarm)
    }

    fun delete(alarm: Alarm)
    {
        repository.delete(alarm)
    }
}
