package moe.misakachan.runway.viewModels

import android.app.Application
import android.widget.SeekBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import moe.misakachan.runway.models.Alarm
import moe.misakachan.runway.repositories.AlarmRepository

class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AlarmRepository(application)
    private val alarms = repository.getAll()
    private val alarmsList = alarms.value
    private val currentAlarm = alarmsList?.get(0)

    /*
        Mutable Observable (origin data)

    var hour : Int,
    var min : Int,
    var color : String,
    var volume : Int,
    var weekday : String,
    val stuff : String
    */

    private val _hour = MutableLiveData<Int>()
    private val _minute = MutableLiveData<Int>()
    private val _color = MutableLiveData<String>()
    val volume = MutableLiveData<Int>()
    private val _weekDay = MutableLiveData<String>()
    private val _stuff = MutableLiveData<String>()

    /*
      Observable Fields for out
      LiveData protects setter, so View can't change any value
    */

    val hour: LiveData<Int> get() = _hour
    val minute: LiveData<Int> get() = _minute
    val color: LiveData<String> get() = _color
    val weekDay : LiveData<String> get() = _weekDay
    val stuff : LiveData<String> get() = _stuff

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
