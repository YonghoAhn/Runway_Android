package moe.misakachan.runway.fragments

import android.graphics.Color
import android.graphics.ColorFilter
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.alarm_fragment.*
import kotlinx.android.synthetic.main.alarm_fragment.view.*
import moe.misakachan.runway.models.Alarm

import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.AlarmViewModel
import org.jetbrains.anko.toast

class AlarmFragment : Fragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
    }

    companion object {
        fun newInstance() = AlarmFragment()
    }

    private lateinit var viewModel: AlarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.alarm_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AlarmViewModel::class.java)
        viewModel.getDataSnapshotLiveData().observe(this) {
            imgAlarmMood.setColorFilter(Color.parseColor("#"+it.color))
            tvAlarmHour.text = it.hour
            tvAlarmMin.text = it.min
            seekBarVolume.progress = it.volume
            toggleMonday.isChecked = it.mon
            toggleTuesday.isChecked = it.tue
            toggleWednesday.isChecked = it.wed
            toggleThursday.isChecked = it.thu
            toggleFriday.isChecked = it.fri
            toggleSaturday.isChecked = it.sat
            toggleSunday.isChecked = it.sun
        }

        seekBarVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                viewModel.setVolume(p1)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        tvAlarmHour.setOnClickListener {
            requireActivity().toast("Hour")
        }

        tvAlarmMin.setOnClickListener {
            requireActivity().toast("Min")
        }

        imgAlarmMood.setOnClickListener {
            requireActivity().toast("Image")
        }

        toggleMonday.setOnClickListener{
            viewModel.setWeekDay("mon",toggleMonday.isChecked)
        }
        toggleTuesday.setOnClickListener{
            viewModel.setWeekDay("tue",toggleTuesday.isChecked)
        }
        toggleWednesday.setOnClickListener{
            viewModel.setWeekDay("wed",toggleWednesday.isChecked)
        }
        toggleThursday.setOnClickListener{
            viewModel.setWeekDay("thu",toggleThursday.isChecked)
        }
        toggleFriday.setOnClickListener {
            viewModel.setWeekDay("fri",toggleFriday.isChecked)
        }
        toggleSaturday.setOnClickListener{
            viewModel.setWeekDay("sat",toggleSaturday.isChecked)
        }
        toggleSunday.setOnClickListener{
            viewModel.setWeekDay("sun",toggleSunday.isChecked)
        }
    }

}
