package com.example.livescoresdu.uilibrary.values

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ffinbank.myfreedom.uilibrary.values.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CustomBottomNavigation(navController: NavController, scope: CoroutineScope) {
    val items = listOf(
        MainDestinations.HOME,
        MainDestinations.OPERATIONS,
        MainDestinations.SERVICES,
//        MainDestinations.CHAT,
//        MainDestinations.MORE
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Column {
        Divider(color = Base200)
        Row(modifier = Modifier
            .background(color = Base900)) {
            items.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.destination,
                    icon = item.icon,
                    name = item.title,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        scope.launch {
                            navController.navigate(item.destination) {
                                navController.graph.startDestinationRoute?.let { screen_route ->
                                    popUpTo(screen_route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    })
            }
        }
    }

//    BottomNavigation(
//        backgroundColor = Base100,
//        contentColor = Green500,
//        modifier = Modifier.advancedShadow()
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//        items.forEach { item ->
//
//            BottomNavigationItem(
//                icon = { Icon(painterResource(id = item.icon), contentDescription = item.destination, tint = if (currentRoute == item.destination) Green500 else Base900.copy(0.4f)) },
//                label = {
//                    Column {
//                        Spacer(modifier = Modifier.height(spacing8))
//                        Text(
//                            text = item.title,
//                            fontSize = fontSize11,
//                            fontWeight = if (currentRoute == item.destination) FontWeight.Medium else FontWeight.Normal,
//                            style = if (currentRoute == item.destination) medium else regular
//                        )
//                    }
//                },
//                selectedContentColor = Green500,
//                unselectedContentColor = Base900.copy(0.4f),
//                alwaysShowLabel = true,
//                selected = currentRoute == item.destination,
//                onClick = {
//                    scope.launch {
//                        navController.navigate(item.destination) {
//                            navController.graph.startDestinationRoute?.let { screen_route ->
//                                popUpTo(screen_route) {
//                                    saveState = true
//                                }
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                }
//            )
//        }
//    }
}

@Composable
private fun BottomNavigationItem(icon: Int, name: String, selected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Column(modifier = modifier
        .click { onClick() }
        .padding(top = spacing8, bottom = spacing8), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(painterResource(id = icon), contentDescription = null, tint = if (selected) Orange500 else Base50.copy(0.4f))
        Spacer(modifier = Modifier.height(spacing8))
        Text(
            text = name,
            fontSize = fontSize11,
            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal,
            style = if (selected) medium else regular,
            maxLines = 1,
            color = if (selected) Orange500 else Base50,
            overflow = TextOverflow.Ellipsis
        )
    }
}