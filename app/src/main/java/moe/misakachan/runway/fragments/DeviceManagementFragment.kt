package moe.misakachan.runway.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.device_management_fragment.*

import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.DeviceManagementViewModel

class DeviceManagementFragment : Fragment() {

    companion object {
        fun newInstance() = DeviceManagementFragment()
    }

    private lateinit var viewModel: DeviceManagementViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.device_management_fragment, container, false)
        view.findViewById<MaterialButton>(R.id.btnMirror).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_deviceManagementFragment_to_mirrorManageFragment)
        }

        view.findViewById<MaterialButton>(R.id.btndoorlock).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_deviceManagementFragment_to_doorManageFragment)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeviceManagementViewModel::class.java)

    }

}
