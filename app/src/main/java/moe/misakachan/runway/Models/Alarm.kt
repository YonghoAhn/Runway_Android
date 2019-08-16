package moe.misakachan.runway.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//this is an Entity class.
@Entity(tableName = "alarm")
data class Alarm (
    @PrimaryKey
    var time : String,
    @ColumnInfo(name = "color")
    var color : String,
    @ColumnInfo(name = "volume")
    var volume : Int,
    @ColumnInfo(name = "weekday")
    var weekday : Array<Boolean>,
    @ColumnInfo(name = "stuff")
    val stuff : ArrayList<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Alarm
        if (!weekday.contentEquals(other.weekday)) return false
        return true
    }

    override fun hashCode(): Int {
        return weekday.contentHashCode()
    }

    constructor() : this("0000","FFFFFF",100, Array(7) {true}, arrayListOf<String>())
}