package com.example.livescoresdu.uilibrary.values

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.livescoresdu.presentation.screens.*
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(navController: NavController, bottomNavHide: (Boolean) -> Unit) {
    //Bottom Navigation Destinations
    composable(MainDestinations.HOME.destination) {
        MatchesScreen(
            navController = navController,
            bottomNavHide = bottomNavHide
        )
    }
    composable(MainDestinations.OPERATIONS.destination) {
        FavorutiesScreen(navController)
    }
    composable(MainDestinations.SERVICES.destination) {
        ProfileScreen()
    }
//    composable(MainDestinations.CHAT.destination) {
//        ChatCategoriesScreen(navController = navController)
//    }
//    composable(MainDestinations.MORE.destination) {
//        MoreScreen(navController = navController)
//    }
//    composable(MainDestinations.FOREX_STORIES.destination) {
//        ForexStoriesScreen(navController = navController)
//    }
//    composable(route = HomeDestinations.NO_INTERNET){
//        NoInternetScreen()
//    }
//    composable(
//        route = MoreDestinations.KnowledgeBaseScreen.route
//    ) {
//        KnowledgeBaseScreen(navController = navController)
//    }
    //MainActivity Destinations
}
