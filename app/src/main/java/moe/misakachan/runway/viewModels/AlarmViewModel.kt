package moe.misakachan.runway.viewModels

import android.app.Application
import android.graphics.Color
import androidx.arch.core.util.Function
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import moe.misakachan.runway.models.Alarm
import moe.misakachan.runway.models.Door
import moe.misakachan.runway.utils.FirebaseQueryLiveData

class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private val alarmRef = FirebaseDatabase.getInstance().getReference("/alarm")
    private val alarmLiveData = FirebaseQueryLiveData(alarmRef)
    private val liveData = Transformations.map(alarmLiveData, Deserializer())

    private inner class Deserializer : Function<DataSnapshot, Alarm> {
        override fun apply(dataSnapshot: DataSnapshot): Alarm? {
            return dataSnapshot.getValue<Alarm>(Alarm::class.java)
        }
    }

    fun getDataSnapshotLiveData(): LiveData<Alarm> {
        return liveData
    }

    fun setVolume(volume : Int)
    {
        alarmRef.child("volume").setValue(volume)
    }
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
    val weekDay = MutableLiveData<ArrayList<Boolean>> ()
    val stuff : LiveData<String> get() = _stuff

}
