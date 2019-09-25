package moe.misakachan.runway.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.sleep_pattern_fragment.*

import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.SleepPatternViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SleepPatternFragment : Fragment() {

    companion object {
        fun newInstance() = SleepPatternFragment()
    }

    private lateinit var viewModel: SleepPatternViewModel
    private var calendar: Calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sleep_pattern_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SleepPatternViewModel::class.java)
        val calendar = Calendar.getInstance()
       //Get current day
        viewModel.getSleepDataSnapshotLiveData(year.toString().padStart(4,'0')+month.toString().padStart(2,'0')+day.toString().padStart(2,'0')).observe(this) {
            btnDeepSleep.text = getString(R.string.deepSleep, it.deep.toString())
        }
        //display it first.
    }

}
