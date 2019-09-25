package moe.misakachan.runway.fragments

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.google.android.material.chip.Chip
import com.google.firebase.database.DataSnapshot
import kotlinx.android.synthetic.main.alarm_fragment.*

import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.AlarmViewModel
import moe.misakachan.runway.viewModels.OnDataListener
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.PorterDuff
import android.util.Log
import android.widget.*
import com.facebook.FacebookSdk.getApplicationContext
import kotlinx.android.synthetic.main.add_stuff_dialog.view.*
import kotlinx.android.synthetic.main.set_color_dialog.view.*


class AlarmFragment : Fragment() {

    companion object {
        fun newInstance() = AlarmFragment()
    }

    private lateinit var viewModel: AlarmViewModel
    private val listener = TimePickerDialog.OnTimeSetListener() {view : TimePicker, hourOfDay : Int , minute : Int ->
        viewModel.setTime(hourOfDay,minute)
    }

    fun changeTime()
    {
        val dialog = TimePickerDialog(requireContext(),listener,0,0,true)
        dialog.show()
    }

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

            val chunked = it.color.chunked(2)
            viewModel.redColor = chunked[0].toInt(16)
            viewModel.greenColor = chunked[1].toInt(16)
            viewModel.blueColor = chunked[2].toInt(16)

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

            if(it.isEnabled) {
                btnToggleTimeGroup.check(R.id.btnToggleAlarm)
            }
        }

        viewModel.getStuff(viewModel.stuffRef, object : OnDataListener {
            override fun onSuccess(dataSnapshot: DataSnapshot) {
                stuffChipGroup.removeAllViews()
                for(child in dataSnapshot.children) {
                    val mChip = requireActivity().layoutInflater.inflate(R.layout.item_chip_stuff,stuffChipGroup,false) as Chip
                    mChip.text = child.value.toString()
                    mChip.setOnCloseIconClickListener {
                        viewModel.removeStuff((it as Chip).text.toString())
                        stuffChipGroup.removeView(it)
                    }
                    stuffChipGroup.addView(mChip)
                }
            }
            override fun onStart() {}
            override fun onFailure() {}
        })

        seekBarVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                viewModel.setVolume(p1)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        tvAlarmHour.setOnClickListener {
            changeTime()
        }

        tvAlarmMin.setOnClickListener {
            changeTime()
        }

        imgAlarmMood.setOnClickListener {
            val inflater =
                getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.set_color_dialog, null)

            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Set new color")
            val imgCircle = view.imgCircle
            val seekBarRed = view.seekBarRed
            val seekBarBlue = view.seekBarBlue
            val seekBarGreen = view.seekBarGreen
            view.imgCircle.colorFilter = imgAlarmMood.colorFilter
            seekBarRed.progress = viewModel.redColor
            seekBarGreen.progress = viewModel.greenColor
            seekBarBlue.progress = viewModel.blueColor

            seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    imgCircle.setColorFilter(Color.argb(255,seekBarRed.progress, seekBarGreen.progress, seekBarBlue.progress), PorterDuff.Mode.SRC_IN)
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    imgCircle.setColorFilter(Color.argb(255,seekBarRed.progress, seekBarGreen.progress, seekBarBlue.progress), PorterDuff.Mode.SRC_IN)
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    imgCircle.setColorFilter(Color.argb(255,seekBarRed.progress, seekBarGreen.progress, seekBarBlue.progress), PorterDuff.Mode.SRC_IN)
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            alert.setView(view)
            alert.setPositiveButton("Add") { _, _ ->
                viewModel.redColor = seekBarRed.progress
                viewModel.greenColor = seekBarGreen.progress
                viewModel.blueColor = seekBarBlue.progress
                imgAlarmMood.setColorFilter(Color.rgb(viewModel.redColor,viewModel.greenColor,viewModel.blueColor))
                viewModel.commitColor()
            }
            alert.setNegativeButton("Cancel") { _, _ -> }
            alert.show()
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

        chipAddStuff.setOnClickListener {
            val inflater =
                getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.add_stuff_dialog, null)

            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Add new stuff")
            val input = view.editTextStuff
            alert.setView(view)
            alert.setPositiveButton("Add") { _, _ ->
                viewModel.addStuff(input.text.toString())
            }
            alert.setNegativeButton("Cancel") { _, _ -> }
            alert.show()
        }

        btnToggleAlarm.setOnClickListener {
            if(btnToggleTimeGroup.checkedButtonId == it.id) {
                //눌러서 체크되어있음
                btnToggleAlarm.text = "ENABLED"
                viewModel.updateState(state = true)
            }
            else
            {
                //체크안됨
                btnToggleAlarm.text = "DISABLED"
                viewModel.updateState(state = false)
            }
        }

        /*
        btnToggleTimeGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when(isChecked)
            {
                true -> {
                }
                false -> {
                }
            }
        }
         */
    }

}
