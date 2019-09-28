package moe.misakachan.runway.viewModels

import androidx.lifecycle.ViewModel;
import com.google.firebase.database.FirebaseDatabase

class SettingViewModel : ViewModel() {
    fun setHomeLocation(location:String)
    {
        FirebaseDatabase.getInstance().getReference("/location").child("/home").setValue(location)
    }

    fun setDestinationLocation(location: String)
    {
        FirebaseDatabase.getInstance().getReference("/location").child("/dest").setValue(location)
    }

    fun setTime(hourOfDay: Int, minute: Int) {
        FirebaseDatabase.getInstance().getReference("/workhour").child("hour").setValue(hourOfDay.toString().padStart(2,'0'))
        FirebaseDatabase.getInstance().getReference("/workhour").child("min").setValue(minute.toString().padStart(2,'0'))
    }
}
