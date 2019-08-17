package moe.misakachan.runway.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//this is an Entity class.
@Entity(tableName = "alarm")
data class Alarm (
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    @ColumnInfo(name = "hour")
    var hour : Int,
    @ColumnInfo(name = "min")
    var min : Int,
    @ColumnInfo(name = "color")
    var color : String,
    @ColumnInfo(name = "volume")
    var volume : Int,
    @ColumnInfo(name = "weekday")
    var weekday : String,
    @ColumnInfo(name = "stuff")
    val stuff : String
) {
    constructor() : this(null,0,0, "FFFFFF",100, "", "")
}