package com.lineup.app.data

import androidx.room.*
import com.lineup.app.models.homeScreenObject
import kotlinx.coroutines.flow.Flow

@Dao
interface LineUpDatabaseDAO{

    @Query("SELECT * FROM category_table")
    fun getCategories(): Flow<List<homeScreenObject>>

    @Query("SELECT * FROM category_table WHERE category_id=:category_id")
    suspend fun getCategory(category_id:String): homeScreenObject

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(homeScreenObject: homeScreenObject)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCategory(homeScreenObject: homeScreenObject)

    @Query("DELETE from category_table")
    suspend fun deleteAllCategory()

    @Delete
    suspend fun deleteCategory(homeScreenObject: homeScreenObject)

}
