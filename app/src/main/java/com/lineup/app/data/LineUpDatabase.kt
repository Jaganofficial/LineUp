package com.lineup.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lineup.app.models.homeScreenObject
import com.lineup.app.models.LineUpObject

@Database(entities = [homeScreenObject::class,LineUpObject::class], version = 2, exportSchema = false)
abstract class LineUpDatabase: RoomDatabase() {
    abstract fun lineUpDAO(): LineUpDatabaseDAO
}