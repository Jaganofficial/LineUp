package com.lineup.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lineup.app.screens.DetailsScreen.DetailsScreen
import com.lineup.app.screens.DetailsScreen.DetailsScreenViewModel
import com.lineup.app.screens.HomeScreen.HomeScreen
import com.lineup.app.screens.HomeScreen.HomeScreenViewModel

@Composable
fun navigationController (homeScreenViewModel: HomeScreenViewModel,detailsScreenViewModel:DetailsScreenViewModel) {
    val navigationController= rememberNavController()
    NavHost(navController = navigationController, startDestination = LineUpScreens.HomeScreen.name){
        composable(route = LineUpScreens.HomeScreen.name){
            HomeScreen(homeScreenViewModel = homeScreenViewModel,navigationController=navigationController)
        }
        composable(route=LineUpScreens.DetailsScreen.name+"/{category_id}", arguments = listOf(
            navArgument(name="category_id"){
                type= NavType.StringType
            })){
            DetailsScreen(detailsScreenViewModel=detailsScreenViewModel,it.arguments?.getString("category_id"))
        }
    }
}