package com.lineup.app.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.lineup.app.navigation.navigationController
import com.lineup.app.screens.DetailsScreen.DetailsScreenViewModel
import com.lineup.app.screens.HomeScreen.HomeScreenViewModel
import com.lineup.app.ui.theme.LineUpTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LineUpTheme() {
                val homeScreenViewModel:HomeScreenViewModel by viewModels()
                val detailsScreenViewModel:DetailsScreenViewModel by viewModels()
                LineUpApp(homeScreenViewModel = homeScreenViewModel,detailsScreenViewModel=detailsScreenViewModel)
            }
        }
    }

    @Composable
    fun LineUpApp(homeScreenViewModel: HomeScreenViewModel, detailsScreenViewModel: DetailsScreenViewModel) {
        navigationController(homeScreenViewModel=homeScreenViewModel, detailsScreenViewModel = detailsScreenViewModel)
    }
}


