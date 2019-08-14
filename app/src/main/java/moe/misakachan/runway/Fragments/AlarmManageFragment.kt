package moe.misakachan.runway.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import moe.misakachan.runway.R
import moe.misakachan.runway.ViewModels.AlarmManageViewModel

class AlarmManageFragment : Fragment() {

    companion object {
        fun newInstance() = AlarmManageFragment()
    }

    private lateinit var viewModel: AlarmManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.alarm_manage_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AlarmManageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
