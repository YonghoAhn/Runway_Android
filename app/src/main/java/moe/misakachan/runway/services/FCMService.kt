package moe.misakachan.runway.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import moe.misakachan.runway.activities.AlarmRingActivity

class FCMService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        FirebaseDatabase.getInstance().getReference("/fcmToken").setValue(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        if(p0.notification!!.title!! == "Alarm Ring")
        {
            FirebaseDatabase.getInstance().getReference("/triggered").setValue(true)
            startActivity(Intent(applicationContext,AlarmRingActivity::class.java))
        }
        Log.d("MisakaMOE", p0.notification!!.title!!)
    }

}
