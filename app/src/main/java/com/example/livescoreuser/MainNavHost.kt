package com.example.livescoreuser

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.livescoresdu.uilibrary.values.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ffinbank.myfreedom.uilibrary.values.Base100


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun  MainNavHost(
    navController: NavHostController,
    startDestination: String,
    startRoute: String,
    onLogged: () -> Unit,
){
    val bottomNavShow = remember {
        mutableStateOf(true)
    }
    AnimatedNavHost(
        modifier = Modifier.fillMaxSize().background(Base100),
        navController = navController,
        startDestination = startRoute,
        enterTransition = { fadeIn(tween(600)) },
        exitTransition = { fadeOut(tween(600)) }
    ) {

        navigation(
            startDestination = HomeDestinations.MATCH_DETAIL,
            route = CARD_ROUTE_ARGS
        ){
            homeGraph(navController,
                    bottomNavHide = {
                        bottomNavShow.value = !it
                    })
        }
        navigation(
            startDestination = MainDestinations.MAIN.destination,
            route = MAIN_ROUTE
        ) {
            composable(route = MainDestinations.MAIN.destination) {
                MainScreen(
                    navController = navController,
                )
            }
        }

    }


}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(navController: NavHostController,) {
    val scope = rememberCoroutineScope()
    val n = rememberAnimatedNavController()
    val bottomNavShow = remember {
        mutableStateOf(true)
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        CompositionLocalProvider(LocalNavController provides navController) {
            AnimatedNavHost(
                modifier = Modifier.fillMaxSize(),
                navController = n,
                startDestination = MainDestinations.HOME.destination
            ) {

                mainGraph(
                    navController = navController,
                    bottomNavHide = {
                        bottomNavShow.value = !it
                    }
                )
            }
        }
        if (bottomNavShow.value) {
            CustomBottomNavigation(navController = n, scope = scope)
        }
    }
}
