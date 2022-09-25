package com.lineup.app.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.lineup.app.navigation.navigationController
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
                LineUpApp(homeScreenViewModel = homeScreenViewModel)
            }
        }
    }

    @Composable
    fun LineUpApp(homeScreenViewModel: HomeScreenViewModel) {
        navigationController(homeScreenViewModel)
    }
}


