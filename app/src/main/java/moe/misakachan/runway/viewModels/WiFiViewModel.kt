package moe.misakachan.runway.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class WiFiViewModel : ViewModel() {
    val ssid =  MutableLiveData<String>()
    val pw = MutableLiveData<String>()

}
