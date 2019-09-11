package moe.misakachan.runway.models

data class Mirror(
    var time:Boolean,
    var traffic : Boolean,
    var weather : Boolean,
    var schedule:Boolean
){
    constructor() : this(false,false,false,false)
}