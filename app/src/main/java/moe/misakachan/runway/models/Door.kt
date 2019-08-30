package moe.misakachan.runway.models

data class Door(
    var isLocked : Boolean
) {
    constructor() : this(
        isLocked = false
    )
}