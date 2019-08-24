package moe.misakachan.runway.viewModels

import android.view.View
import androidx.lifecycle.ViewModel;
import com.facebook.login.LoginManager

class LoginViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }

    fun onClick(view: View)
    {
        val loginManager = LoginManager.getInstance()
    }
}
