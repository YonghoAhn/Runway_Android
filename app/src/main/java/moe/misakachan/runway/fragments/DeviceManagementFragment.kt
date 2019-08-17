package moe.misakachan.runway.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import moe.misakachan.runway.R
import moe.misakachan.runway.ViewModels.DeviceManagementViewModel

class DeviceManagementFragment : Fragment() {

    companion object {
        fun newInstance() = DeviceManagementFragment()
    }

    private lateinit var viewModel: DeviceManagementViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.device_management_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeviceManagementViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
