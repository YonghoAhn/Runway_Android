package moe.misakachan.runway.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import moe.misakachan.runway.R
import moe.misakachan.runway.ViewModels.PickAddressViewModel

class PickAddressFragment : Fragment() {

    companion object {
        fun newInstance() = PickAddressFragment()
    }

    private lateinit var viewModel: PickAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pick_address_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PickAddressViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
