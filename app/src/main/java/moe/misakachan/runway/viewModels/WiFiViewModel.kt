package moe.misakachan.runway.viewModels

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class WiFiViewModel(application: Application) : AndroidViewModel(application) {
    val ssid =  MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun onClick(view : View)
    {
        if (ssid.value != null)
        {
            getApplication<Application>().getSharedPreferences("RunwaySetting",0).edit().putString("SSID",ssid.value).putString("PW",pw.value).apply()
        }
    }
}
