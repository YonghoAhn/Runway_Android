package moe.misakachan.runway.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import moe.misakachan.runway.Models.Alarm
import moe.misakachan.runway.Repositories.AlarmRepository

class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AlarmRepository(application)
    private val alarms = repository.getAll()

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
