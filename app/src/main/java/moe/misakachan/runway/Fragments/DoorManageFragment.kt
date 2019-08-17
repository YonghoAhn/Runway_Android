package moe.misakachan.runway.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.DoorManageViewModel

class DoorManageFragment : Fragment() {

    companion object {
        fun newInstance() = DoorManageFragment()
    }

    private lateinit var viewModel: DoorManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.door_manage_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DoorManageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
