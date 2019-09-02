package moe.misakachan.runway.fragments

import android.graphics.Color
import android.graphics.ColorFilter
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.google.android.material.chip.Chip
import com.google.firebase.database.DataSnapshot
import kotlinx.android.synthetic.main.alarm_fragment.*
import kotlinx.android.synthetic.main.alarm_fragment.view.*
import moe.misakachan.runway.models.Alarm

import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.AlarmViewModel
import moe.misakachan.runway.viewModels.OnDataListener
import org.jetbrains.anko.backgroundColor
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

        viewModel.getStuff(viewModel.stuffRef, object : OnDataListener {
            override fun onSuccess(dataSnapshot: DataSnapshot) {
                stuffChipGroup.removeAllViews()
                for(child in dataSnapshot.children) {
                    val mChip = requireActivity().layoutInflater.inflate(R.layout.item_chip_stuff,null,false) as Chip
                    mChip.text = child.value.toString()

                    /*
                    val chip = Chip(ContextThemeWrapper(requireContext(),R.style.ChipStyle))
                    chip.text = child.value.toString()
                    chip.isCheckable = false
                    chip.setTextColor(Color.BLACK)
                    chip.setChipBackgroundColorResource(R.color.secondaryColor)
                    */
                    //var layoutParam = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                    //layoutParam.setMargins()
                    stuffChipGroup.addView(mChip)
                }
            }

            override fun onStart() {
            }

            override fun onFailure() {
            }

        })

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
