package moe.misakachan.runway.models

import android.annotation.SuppressLint

data class Alarm(
    var hour: String,
    var min: String,
    var color: String,
    var volume: Int,
    var mon : Boolean,
    var tue : Boolean,
    var wed : Boolean,
    var thu : Boolean,
    var fri : Boolean,
    var sat : Boolean,
    var sun : Boolean,
    var isEnabled : Boolean
) {
    @SuppressLint("UseSparseArrays")
    constructor() : this("00","00", "FFFFFF",100, false,false,false,false,false,false,false,false)
}