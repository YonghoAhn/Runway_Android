package moe.misakachan.runway.models

data class Door(
    var isLocked : Boolean,
    var uid : String
) {
    constructor() : this(
        isLocked = false, uid = ""
    )
}