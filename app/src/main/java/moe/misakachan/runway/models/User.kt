package moe.misakachan.runway.models

data class User(
    val uid:Int,
    var name: String
) {
    constructor() : this(0,"")
}