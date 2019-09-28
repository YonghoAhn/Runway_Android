package moe.misakachan.runway.fragments

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.facebook.FacebookSdk
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.add_stuff_dialog.view.*
import kotlinx.android.synthetic.main.setting_fragment.*

import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.SettingViewModel
import android.widget.TimePicker
import android.app.TimePickerDialog
import java.lang.StringBuilder


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

        }

        view.findViewById<MaterialButton>(R.id.btnDestination).setOnClickListener {
            val alertInflater =
                requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val alertView = alertInflater.inflate(R.layout.add_stuff_dialog, null)
            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Enter your address")
            val input = view.editTextStuff
            alert.setView(view)
            alert.setPositiveButton("Add") { _, _ ->
                viewModel.setDestinationLocation(input.text.toString())
            }
            alert.setNegativeButton("Cancel") { _, _ -> }
            alert.show()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingViewModel::class.java)
        btnHomeLocation.setOnClickListener {
            showAlert(true)
        }

        btnDestination.setOnClickListener {
            showAlert(false)
        }

        btnTime.setOnClickListener {
            showTime()
        }
    }

    private val listener = TimePickerDialog.OnTimeSetListener() {view : TimePicker, hourOfDay : Int , minute : Int ->
        viewModel.setTime(hourOfDay,minute)
    }

    private fun showTime() {
        val timePickerDialog = TimePickerDialog(requireContext(), listener, 0, 0, true
        )
        timePickerDialog.setMessage("출근 시간을 지정하세요.")
        timePickerDialog.show()
    }

    private fun showAlert(switch:Boolean)
    {
        val alertInflater =
            requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val alertView = alertInflater.inflate(R.layout.add_stuff_dialog, null)
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle("Enter your address")
        val input = alertView.editTextStuff
        alert.setView(alertView)
        alert.setPositiveButton("Add") { _, _ ->
            if(switch)
                viewModel.setHomeLocation(input.text.toString())
            else
                viewModel.setDestinationLocation(input.text.toString())
        }
        alert.setNegativeButton("Cancel") { _, _ -> }
        alert.show()
    }
}
