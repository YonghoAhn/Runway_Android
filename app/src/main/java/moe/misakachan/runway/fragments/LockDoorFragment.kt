package moe.misakachan.runway.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.lock_door_fragment.*

import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.LockDoorViewModel
import java.util.concurrent.Executor

class LockDoorFragment : Fragment() {

    companion object {
        fun newInstance() = LockDoorFragment()
    }

    private lateinit var viewModel: LockDoorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lock_door_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LockDoorViewModel::class.java)
        btnLockDoor.setOnClickListener{
            runFingerprint()
        }
    }

    fun runFingerprint(){
        val biometricPromptInfo : BiometricPrompt.PromptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Unlock door")
            .setDescription("Test")
            .setSubtitle("Subtitle")
            .setNegativeButtonText("Cancel")
            .build()
        val authenticationCallback = getAuthenticationCallback()
        val biometricPrompt = BiometricPrompt(requireActivity(), Executor {  }, authenticationCallback)
        biometricPrompt.authenticate(biometricPromptInfo)
    }


    private fun getAuthenticationCallback() = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            Log.d("MisakaMOE","Error")

        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            Log.d("MisakaMOE","Success")
            Toast.makeText(requireContext(),"Successful",Toast.LENGTH_SHORT).show()
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            Log.d("MisakaMOE","Fail")

            Toast.makeText(requireContext(),"Failed",Toast.LENGTH_SHORT).show()
        }
    }

}
