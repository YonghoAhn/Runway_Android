package moe.misakachan.runway.models

import androidx.room.Entity

@Entity(tableName = "Wifi")
data class Wifi(
    val SSID : String,
    val PW : String
)