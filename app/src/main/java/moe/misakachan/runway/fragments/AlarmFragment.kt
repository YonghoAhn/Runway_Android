package moe.misakachan.runway.fragments

import android.app.AlertDialog
import android.content.DialogInterface
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
import android.widget.EditText
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
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.PorterDuff
import com.facebook.FacebookSdk.getApplicationContext
import kotlinx.android.synthetic.main.add_stuff_dialog.view.*
import kotlinx.android.synthetic.main.set_color_dialog.view.*


class AlarmFragment : Fragment() {

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

            val chunked = it.color.chunked(2)
            viewModel.redColor = chunked[0].toInt(16)
            viewModel.greenColor = chunked[1].toInt(16)
            viewModel.blueCoolor = chunked[2].toInt(16)

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
            requireActivity().toast("Hour")
        }

        tvAlarmMin.setOnClickListener {
            requireActivity().toast("Min")
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
            seekBarBlue.progress = viewModel.blueCoolor

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
                viewModel.blueCoolor = seekBarBlue.progress
                imgAlarmMood.setColorFilter(Color.rgb(viewModel.redColor,viewModel.greenColor,viewModel.blueCoolor))
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



        btnToggleTimeGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when(isChecked)
            {
                true -> {
                    btnToggleTime.text = "ENABLED"
                }
                false -> {
                    btnToggleTime.text = "DISABLED"
                }
            }
        }
    }

}
