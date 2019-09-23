package moe.misakachan.runway.utils

import androidx.arch.core.util.Function
import com.google.firebase.database.DataSnapshot

class FirebaseDeserializer : Function<DataSnapshot, Any> {
    override fun apply(dataSnapshot: DataSnapshot): Any? {
        return dataSnapshot.getValue<Any>(Any::class.java)
    }
}