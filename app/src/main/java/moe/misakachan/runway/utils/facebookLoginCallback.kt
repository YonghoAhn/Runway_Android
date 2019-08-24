package moe.misakachan.runway.utils

import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import android.os.Bundle
import android.util.Log
import com.facebook.GraphResponse
import org.json.JSONObject
import com.facebook.GraphRequest
import com.facebook.AccessToken



class facebookLoginCallback : FacebookCallback<LoginResult> {
    override fun onSuccess(result: LoginResult?) {

    }

    override fun onCancel() {

    }

    override fun onError(error: FacebookException?) {

    }

    fun requestMe(token: AccessToken) {
        val graphRequest = GraphRequest.newMeRequest(
            token
        ) { `object`, response -> Log.e("result", `object`.toString()) }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,gender,birthday")
        graphRequest.parameters = parameters
        graphRequest.executeAsync()
    }
}