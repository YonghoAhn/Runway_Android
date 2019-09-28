package moe.misakachan.runway.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_alarm_ring.*
import moe.misakachan.runway.R

class AlarmRingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_ring)
        btnStopAlarm.setOnClickListener {
            FirebaseDatabase.getInstance().getReference("/triggered").setValue(false)
            finish()
        }
    }
}
