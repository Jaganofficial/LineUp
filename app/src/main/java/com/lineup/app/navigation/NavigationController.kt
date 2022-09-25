package com.lineup.app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lineup.app.screens.DetailsScreen.DetailsScreen
import com.lineup.app.screens.HomeScreen.HomeScreen
import com.lineup.app.screens.HomeScreen.HomeScreenViewModel
import javax.inject.Inject

@Composable
fun navigationController (homeScreenViewModel: HomeScreenViewModel) {
    val navigationController= rememberNavController()
    NavHost(navController = navigationController, startDestination = LineUpScreens.HomeScreen.name){
        composable(route = LineUpScreens.HomeScreen.name){
            HomeScreen(homeScreenViewModel = homeScreenViewModel)
        }
        composable(route=LineUpScreens.DetailsScreen.name){
            DetailsScreen()
        }
    }
}