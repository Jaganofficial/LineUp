package com.lineup.app.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lineup.app.utils.getToday
import java.util.*

@Entity(tableName = "category_table")
data class homeScreenObject(
    @PrimaryKey
    @ColumnInfo(name = "category_id")
    val category_Id:String=UUID.randomUUID().toString(),
    @ColumnInfo(name = "category_name")
    val category_Name:String,
    @ColumnInfo(name="added_date")
    val added_date: String = getToday()
)
