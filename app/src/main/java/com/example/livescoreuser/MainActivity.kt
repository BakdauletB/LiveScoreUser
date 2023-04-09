package com.example.livescoreuser

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.example.livescoresdu.uilibrary.values.MAIN_ROUTE
import com.example.livescoresdu.uilibrary.values.MainDestinations
import com.example.livescoresdu.uilibrary.values.SharedPreferencesHelper
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ffinbank.myfreedom.uilibrary.values.Base100


class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
            SharedPreferencesHelper.init(this)

        setContent {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {

    val navController = rememberAnimatedNavController()

    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier
        .fillMaxSize()
        .background(Base100)) {

        MainNavHost(
            navController = navController,
            startRoute =  MAIN_ROUTE ,
            startDestination =  MainDestinations.HOME.destination,
            onLogged = {
                navController.navigate(
                    route = MAIN_ROUTE,
                    builder = {
                        popUpTo(MainDestinations.HOME.destination, popUpToBuilder = {
                            inclusive = true
                        })
                    }
                )
            }
        )
    }


}
