package moe.misakachan.runway.models

import com.google.firebase.database.PropertyName

class Door {
    @set:PropertyName("uid")
    @get:PropertyName("uid")
    var uid: String = ""
    @set:PropertyName("isLocked")
    @get:PropertyName("isLocked")
    var isLocked: Boolean = false

    override fun toString(): String {
        return "{Door uid=$uid isLocked=$isLocked}"
    }
}