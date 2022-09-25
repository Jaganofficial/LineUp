package com.lineup.app.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lineup.app.utils.getToday
import java.util.*

@Entity
data class LineUpObject(
    @PrimaryKey
    @ColumnInfo
    val line_up_object_id:String=UUID.randomUUID().toString(),
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var year: String,
    @ColumnInfo
    var platform: String="",
    @ColumnInfo
    var genre: String="",
    @ColumnInfo
    var description: String="",
    @ColumnInfo
    var image_url: String="",
    @ColumnInfo
    var addedDate: String = getToday(),
    @ColumnInfo
    val category_id:String
)
