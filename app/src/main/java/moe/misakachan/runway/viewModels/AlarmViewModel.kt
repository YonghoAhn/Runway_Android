package moe.misakachan.runway.viewModels

import android.app.Application
import android.graphics.Color
import android.util.Log
import androidx.arch.core.util.Function
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.google.firebase.database.*
import moe.misakachan.runway.models.Alarm
import moe.misakachan.runway.models.Door
import moe.misakachan.runway.utils.FirebaseQueryLiveData

interface OnDataListener {
    fun onSuccess(dataSnapshot: DataSnapshot)
    fun onStart()
    fun onFailure()

}

class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private val alarmRef = FirebaseDatabase.getInstance().getReference("/alarm")
    private val alarmLiveData = FirebaseQueryLiveData(alarmRef)
    private val liveData = Transformations.map(alarmLiveData, Deserializer())

    val stuffRef = FirebaseDatabase.getInstance().getReference("/stuff")


    private inner class Deserializer : Function<DataSnapshot, Alarm> {
        override fun apply(dataSnapshot: DataSnapshot): Alarm? {
            return dataSnapshot.getValue<Alarm>(Alarm::class.java)
        }
    }

    fun getDataSnapshotLiveData(): LiveData<Alarm> {
        return liveData
    }

    fun getStuff(ref : DatabaseReference, listener: OnDataListener)
    {
        listener.onStart()
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                listener.onFailure()
            }

            override fun onDataChange(p0: DataSnapshot) {
                listener.onSuccess(p0)
            }
        })
    }

    fun setVolume(volume : Int)
    {
        alarmRef.child("volume").setValue(volume)
    }

    fun setWeekDay(position : String, state : Boolean)
    {
        alarmRef.child(position).setValue(state)
    }

    fun removeStuff(key : String)
    {
        stuffRef.child(key).removeValue()
    }

    fun addStuff(key:String)
    {
        Log.d("MisakaMOE",key)
        stuffRef.child(key).setValue(key)
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
