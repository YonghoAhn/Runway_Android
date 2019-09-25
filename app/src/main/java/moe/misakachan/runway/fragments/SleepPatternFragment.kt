package moe.misakachan.runway.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.sleep_pattern_fragment.*

import moe.misakachan.runway.R
import moe.misakachan.runway.models.SleepPattern
import moe.misakachan.runway.viewModels.SleepPatternViewModel
import java.lang.StringBuilder
import java.text.SimpleDateFormat
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

        viewModel.dateLiveData.observe(this) {
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val sleepPattern = p0.getValue(SleepPattern::class.java)
                    //minute to hour and min
                    if (sleepPattern != null) {
                        btnDeepSleep.text = getString(R.string.deepSleep, parseSleepTime(sleepPattern.deep) )
                        btnLightSleep.text = getString(R.string.lightSleep, parseSleepTime(sleepPattern.light) )
                        btnNoSleep.text = getString(R.string.nonSleep, parseSleepTime(sleepPattern.non) )
                    }

                }
            }
        }

        btnNextDate.setOnClickListener {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            viewModel.setDate(year.toString().padStart(4,'0') + month.toString().padStart(2, '0') + day.toString().padStart(2, '0'))
        }

        btnPrevDate.setOnClickListener {
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            viewModel.setDate(year.toString().padStart(4,'0') + month.toString().padStart(2, '0') + day.toString().padStart(2, '0'))
        }
        //display it first.
    }

    fun parseSleepTime(minute: Int) : String
    {
        val returnTime = StringBuilder()
        val hour : Int = minute / 60
        if(hour == 0) {
            returnTime.append(minute)
            returnTime.append(" M")
        }
        else
        {
            val min = (minute - (hour*60)) % 60
            returnTime.append(hour)
            returnTime.append(" H ")
            returnTime.append(min)
            returnTime.append(" M")
        }
        return returnTime.toString()
    }

}
