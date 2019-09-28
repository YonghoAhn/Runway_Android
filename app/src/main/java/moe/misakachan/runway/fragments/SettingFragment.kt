package moe.misakachan.runway.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton

import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.SettingViewModel

class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    private lateinit var viewModel: SettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.setting_fragment, container, false)

        view.findViewById<MaterialButton>(R.id.btnHomeLocation).setOnClickListener {
            val bundle = bundleOf("type" to false)
            Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_pickAddressFragment2, bundle)
        }

        view.findViewById<MaterialButton>(R.id.btnDestination).setOnClickListener {
            val bundle = bundleOf("type" to true)
            Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_timePickFragment2, bundle)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingViewModel::class.java)

    }

}
