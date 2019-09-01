package moe.misakachan.runway.models

data class Alarm (
    var hour : String,
    var min : String,
    var color : String,
    var volume : Int,
    var weekday : String,
    val stuff : HashMap<String, String>
) {
    constructor() : this("00","00", "FFFFFF",100, "", HashMap<String, String>())
}