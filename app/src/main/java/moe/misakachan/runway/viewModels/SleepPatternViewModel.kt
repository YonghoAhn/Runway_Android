package moe.misakachan.runway.viewModels

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import moe.misakachan.runway.models.Alarm
import moe.misakachan.runway.models.SleepPattern
import moe.misakachan.runway.utils.FirebaseQueryLiveData

class SleepPatternViewModel : ViewModel() {

    var dateLiveData : MutableLiveData<String> = MutableLiveData("20190924")

    fun setDate(date:String)
    {
        dateLiveData.value = date
    }

    // /sleepPattern/date/{deep, light, non}
    // date to model , ref from cur date.
    // get date from request, It's not LiveData, just return value : ref is keep changing.
    private inner class Deserializer : Function<DataSnapshot, SleepPattern> {
        override fun apply(dataSnapshot: DataSnapshot): SleepPattern? {
            return dataSnapshot.getValue<SleepPattern>(SleepPattern::class.java)
        }
    }

    fun getSleepDataSnapshotLiveData(date : String) : LiveData<SleepPattern>
    {
        val sleepRef = FirebaseDatabase.getInstance().getReference("/sleepPattern/$date")
        val sleepLiveData = FirebaseQueryLiveData(sleepRef)
        return Transformations.map(sleepLiveData, Deserializer())
    }

}
