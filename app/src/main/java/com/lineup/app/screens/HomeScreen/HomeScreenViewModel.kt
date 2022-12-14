package com.lineup.app.screens.HomeScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lineup.app.LineUpApplication
import com.lineup.app.models.homeScreenObject
import com.lineup.app.repository.LineUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.security.AccessController.getContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val lineUpRepository: LineUpRepository) :
    ViewModel() {

    private val _homeScreenCategories = MutableStateFlow<List<homeScreenObject>>(emptyList())
    val homeScreenCategories = _homeScreenCategories.asStateFlow()

    var showAddCategoryDialogue= mutableStateOf(false)
    var showUpdateCategoryDialogue= mutableStateOf(false)

    var currentCategory:homeScreenObject?=null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            lineUpRepository.getAllCategories().distinctUntilChanged().collect {
                if (it==null) {
                    Log.d("LINEUPAPP","@HomeScreenViewModel 27 received null or empty data from database for home screen")
                } else {
                    _homeScreenCategories.value = it
                }
            }
        }
    }

     fun getCategory(category_id: String) {
        viewModelScope.launch {
            lineUpRepository.getCategory(category_id)
        }
    }

     fun addCategory(homeScreenObject: homeScreenObject) {
        viewModelScope.launch {
            lineUpRepository.addCategory(homeScreenObject)
        }
    }

     fun updateCategory(homeScreenObject: homeScreenObject) {
        viewModelScope.launch {
         lineUpRepository.updateCategory(homeScreenObject)
        }
    }

    fun deleteCategory(homeScreenObject: homeScreenObject) {
        viewModelScope.launch {
        lineUpRepository.deleteCategory(homeScreenObject = homeScreenObject)
        }
        deleteAllDetailsInThisCategory(homeScreenObject.category_Id)
    }

    private fun deleteAllDetailsInThisCategory(category_id: String){
        viewModelScope.launch {
            lineUpRepository.deleteAllDetailsInThisCategory(category_id = category_id)
        }
    }

     fun deleteAllCategories() {
        viewModelScope.launch {
         lineUpRepository.deleteAllCategories()
        }
    }

}