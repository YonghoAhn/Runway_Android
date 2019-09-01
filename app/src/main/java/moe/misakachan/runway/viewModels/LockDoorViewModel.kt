package moe.misakachan.runway.viewModels

import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.arch.core.util.Function
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.firebase.database.FirebaseDatabase
import moe.misakachan.runway.models.Door
import moe.misakachan.runway.utils.FirebaseQueryLiveData
import com.google.firebase.database.DataSnapshot
import androidx.lifecycle.Transformations
import moe.misakachan.runway.R


class LockDoorViewModel(application: Application) : AndroidViewModel(application) {
    //state : locked/unlocked
    private val doorRef = FirebaseDatabase.getInstance().getReference("/Door")
    private val doorLiveData = FirebaseQueryLiveData(doorRef)

    fun getDataSnapshotLiveData(): LiveData<Door> {
        return liveData
    }

    fun setState(state : Boolean)
    {
        doorRef.child("/isLocked").setValue(state)
    }

    private val liveData = Transformations.map(doorLiveData, Deserializer())

    private inner class Deserializer : Function<DataSnapshot,Door> {
        override fun apply(dataSnapshot: DataSnapshot): Door? {
            return dataSnapshot.getValue<Door>(Door::class.java)
        }
    }
}
