package moe.misakachan.runway.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.login_fragment.*
import moe.misakachan.runway.R
import moe.misakachan.runway.activities.MainActivity
import moe.misakachan.runway.viewModels.LoginViewModel


class LoginFragment : Fragment() {

    private lateinit var mCallbackManager : CallbackManager
    private lateinit var auth : FirebaseAuth

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
        auth = FirebaseAuth.getInstance()
        mCallbackManager = CallbackManager.Factory.create()
        val currentUser = auth.currentUser
        facebook_signin_btn.setOnClickListener {
            onClick()
        }
        if(currentUser!=null)
        {
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.d("MisakaMOE", "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token

                    // Log and toast
                     Log.d("MisakaMOE", token)
                    FirebaseDatabase.getInstance().getReference("/fcmToken").setValue(token)

                })
            startActivity(Intent(requireContext(),MainActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun onClick()
    {
        val loginManager = LoginManager.getInstance()
        loginManager.logInWithReadPermissions(this, mutableListOf("public_profile", "email"))
        loginManager.registerCallback(mCallbackManager, object :FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                handleFacebookAccessToken(result?.accessToken)
            }
            override fun onCancel() {
            }
            override fun onError(error: FacebookException?) {
            }
        })
    }

    private fun handleFacebookAccessToken(token: AccessToken?) {
        if (token != null) {
            val credential = FacebookAuthProvider.getCredential(token.token)
            auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("MisakaMOE", "signInWithCredential:success")
                        startActivity(Intent(requireContext(),MainActivity::class.java))
                        requireActivity().finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("MisakaMOE", "signInWithCredential:failure", task.exception)
                    }
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
