package com.lineup.app.screens.DetailsScreen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lineup.app.models.LineUpObject
import com.lineup.app.models.homeScreenObject
import com.lineup.app.repository.LineUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(val lineUpRepository: LineUpRepository) :
    ViewModel() {

    var showAddDetailsDialogue= mutableStateOf(false)

    fun getAllDetailsInThisCategory(category_id: String):Flow<List<LineUpObject>>
    {
        return lineUpRepository.getAllDetailsFromTheCategory(category_id = category_id)
    }

    suspend fun getCategory(category_id: String) :homeScreenObject {
           return lineUpRepository.getCategory(category_id = category_id)
    }

    fun addDetail(lineUpObject: LineUpObject){
        viewModelScope.launch {
        lineUpRepository.addDetail(lineUpObject = lineUpObject)
        }
    }

    fun deleteDetail(lineUpObject: LineUpObject)
    {
        viewModelScope.launch {
            lineUpRepository.deleteDetail(lineUpObject = lineUpObject)
        }
    }

    fun deleteAllDetails(category_id: String)
    {
        viewModelScope.launch {
            lineUpRepository.deleteAllDetailsInThisCategory(category_id = category_id)
        }
    }

    fun updateDetail(lineUpObject: LineUpObject)
    {
        viewModelScope.launch {
            lineUpRepository.updateDetail(lineUpObject)
        }
    }
}