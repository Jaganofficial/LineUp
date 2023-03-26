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
import com.lineup.app.screens.MoreDetailsScreen.MoreDetailScreen
import com.lineup.app.screens.MoreDetailsScreen.MoreDetailsScreenViewModel

@Composable
fun navigationController (homeScreenViewModel: HomeScreenViewModel,detailsScreenViewModel:DetailsScreenViewModel, moreDetailsScreenViewModel: MoreDetailsScreenViewModel) {
    val navigationController= rememberNavController()
    NavHost(navController = navigationController, startDestination = LineUpScreens.HomeScreen.name){
        composable(route = LineUpScreens.HomeScreen.name){
            HomeScreen(homeScreenViewModel = homeScreenViewModel,navigationController=navigationController)
        }
        composable(route=LineUpScreens.DetailsScreen.name+"/{category_id}", arguments = listOf(
            navArgument(name="category_id"){
                type= NavType.StringType
            })){
            DetailsScreen(detailsScreenViewModel=detailsScreenViewModel,it.arguments?.getString("category_id"), navigationController = navigationController)
        }
        composable(route=LineUpScreens.MoreDetailsScreen.name+"/{object_id}", arguments = listOf(
            navArgument(name = "object_id"){
                type= NavType.StringType
            }))
        {
            MoreDetailScreen(moreDetailsScreenViewModel=moreDetailsScreenViewModel,navigationController=navigationController,lineup_object_id=it.arguments?.getString("object_id"))
        }
    }
}