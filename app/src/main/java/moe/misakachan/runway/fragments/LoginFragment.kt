package moe.misakachan.runway.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import moe.misakachan.runway.R
import moe.misakachan.runway.utils.facebookLoginCallback
import moe.misakachan.runway.viewModels.LoginViewModel


class LoginFragment : Fragment() {

    private val mCallbackManager = CallbackManager.Factory.create()
    private val mLoginCallback = facebookLoginCallback()

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    public fun onClick(view : View)
    {
        val loginManager = LoginManager.getInstance()
        loginManager.logInWithReadPermissions(this, mutableListOf("public_profile", "email"))
        loginManager.registerCallback(mCallbackManager,mLoginCallback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
