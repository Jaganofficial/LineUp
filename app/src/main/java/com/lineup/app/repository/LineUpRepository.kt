package com.lineup.app.repository

import com.lineup.app.data.LineUpDatabaseDAO
import com.lineup.app.models.LineUpObject
import com.lineup.app.models.homeScreenObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LineUpRepository @Inject constructor(private val lineUpDatabaseDAO: LineUpDatabaseDAO) {

    //Home Screen
    //{

    //Get all categories in the home page
    fun getAllCategories(): Flow<List<homeScreenObject>> =
        lineUpDatabaseDAO.getCategories().flowOn(Dispatchers.IO).conflate()

    //Get a single home screen category
    suspend fun getCategory(category_id: String): homeScreenObject =
        lineUpDatabaseDAO.getCategory(category_id = category_id)

    //Add a home screen category
    suspend fun addCategory(category: homeScreenObject) =
        lineUpDatabaseDAO.addCategory(homeScreenObject = category)

    //Update a category
    suspend fun updateCategory(category: homeScreenObject) =
        lineUpDatabaseDAO.updateCategory(category)

    //Delete all categories in the home page
    suspend fun deleteAllCategories() = lineUpDatabaseDAO.deleteAllCategory()

    //Delete a single category
    suspend fun deleteCategory(homeScreenObject: homeScreenObject) =
        lineUpDatabaseDAO.deleteCategory(homeScreenObject = homeScreenObject)
    //}


    //Details Screen
    //{

    //Get all details for the given category
    fun getAllDetailsFromTheCategory(category_id: String):Flow<List<LineUpObject>> {
        return lineUpDatabaseDAO.getAllDetailsFromThisCategory(category_id = category_id)
    }

    //Get the given detail
    suspend fun getDetail(details_id: String):LineUpObject {
        return lineUpDatabaseDAO.getDetails(details_id = details_id)
    }

    //Add the given detail
    suspend fun addDetail(lineUpObject: LineUpObject) {
        lineUpDatabaseDAO.addDetail(lineUpObject)
    }

    //update the given detail
    suspend fun updateDetail(lineUpObject: LineUpObject) {
        lineUpDatabaseDAO.updateDetail(lineUpObject = lineUpObject)
    }

    //Delete all details from all the category
    suspend fun deleteAllDetailsFromAllCategory() {
        lineUpDatabaseDAO.deleteAllDetailsFromAllCategory()
    }

    //Delete all details from the given category
    suspend fun deleteAllDetailsInThisCategory(category_id: String) {
        lineUpDatabaseDAO.deleteAllDetailsInThisCategory(category_id = category_id)
    }

    //Delete the given details
    suspend fun deleteDetail(lineUpObject: LineUpObject) {
        lineUpDatabaseDAO.deleteDetail(lineUpObject = lineUpObject)
    }

    //}

}