package moe.misakachan.runway.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import moe.misakachan.runway.models.Door

class LockDoorViewModel(application: Application) : AndroidViewModel(application) {
    //state : locked/unlocked
    val door = Door()


}
