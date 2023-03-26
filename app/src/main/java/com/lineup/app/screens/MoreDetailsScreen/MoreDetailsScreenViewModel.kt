package com.lineup.app.screens.MoreDetailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lineup.app.models.LineUpObject
import com.lineup.app.repository.LineUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreDetailsScreenViewModel @Inject constructor(val lineUpRepository: LineUpRepository) : ViewModel() {

    fun UpdateDetails()
    {

    }

    suspend fun getDetail(lineUpObjectId:String):LineUpObject{
       return lineUpRepository.getDetail(lineUpObjectId)
    }

}