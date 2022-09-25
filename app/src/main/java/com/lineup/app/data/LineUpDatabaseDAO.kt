package com.lineup.app.data

import androidx.room.*
import com.lineup.app.models.homeScreenObject
import com.lineup.app.models.LineUpObject
import kotlinx.coroutines.flow.Flow

@Dao
interface LineUpDatabaseDAO{

    //Home Screen

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

    //Details Screen
    @Query("SELECT * FROM LineUpObject WHERE category_id=:category_id")
    fun getAllDetailsFromThisCategory(category_id: String): Flow<List<LineUpObject>>

    @Query("SELECT * FROM LineUpObject WHERE line_up_object_id=:details_id")
    suspend fun getDetails(details_id:String): LineUpObject

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDetail(LineUpObject: LineUpObject)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDetail(lineUpObject: LineUpObject)

    @Query("DELETE from LineUpObject")
    suspend fun deleteAllDetailsFromAllCategory()

    @Query("DELETE from LineUpObject WHERE category_id=:category_id")
    suspend fun deleteAllDetailsInThisCategory(category_id: String)

    @Delete
    suspend fun deleteDetail(lineUpObject: LineUpObject)

}
