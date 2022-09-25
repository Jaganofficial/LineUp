package com.lineup.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lineup.app.models.homeScreenObject

@Database(entities = [homeScreenObject::class], version = 1, exportSchema = false)
abstract class LineUpDatabase: RoomDatabase() {
    abstract fun lineUpDAO(): LineUpDatabaseDAO
}