package moe.misakachan.runway.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.PickAddressViewModel


class PickAddressFragment : Fragment(){
    companion object {
        fun newInstance() = PickAddressFragment()
    }

    private lateinit var viewModel: PickAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pick_address_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PickAddressViewModel::class.java)

    }
}
