package moe.misakachan.runway.models

data class SleepPattern(
    val deep : Int,
    val light : Int,
    val non : Int
){
    constructor() : this(0,0,0)
}