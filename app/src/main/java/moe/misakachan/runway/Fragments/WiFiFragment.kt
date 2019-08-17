package moe.misakachan.runway.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.WiFiViewModel


class WiFiFragment : Fragment() {

    companion object {
        fun newInstance() = WiFiFragment()
    }

    private lateinit var viewModel: WiFiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wi_fi_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WiFiViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
