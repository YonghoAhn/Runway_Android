package moe.misakachan.runway.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.alarm_fragment.*
import moe.misakachan.runway.models.Alarm

import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.AlarmViewModel

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
        val ToggleButtonArray = arrayOf(toggleMonday, toggleTuesday, toggleWednesday, toggleThursday, toggleFriday, toggleSaturday, toggleSunday)

        viewModel = ViewModelProviders.of(this).get(AlarmViewModel::class.java)
        viewModel.getAll().observe(this, Observer<List<Alarm>> { alarms ->



            val weekDay = alarms[0].weekday
            for(i in  0..6)
            {
                ToggleButtonArray[i].isChecked = weekDay[i] != '0'
            }
        })
    }

}
