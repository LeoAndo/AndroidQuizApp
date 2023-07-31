package com.leoleo.androidapptemplate.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leoleo.androidapptemplate.ui.completed.CompletedTasksScreen
import com.leoleo.androidapptemplate.ui.top.TopScreen

@Composable
internal fun MyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TopDestinations.TopRoute.routeName,
    windowWidthSizeClass: WindowWidthSizeClass,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = TopDestinations.TopRoute.routeName,
            content = {
                TopScreen(
                    navigateToMainScreen = {
                        navController.navigate(TopDestinations.MainRoute.routeName)
                    }, navigateToCompletedTasksScreen = {
                        navController.navigate(TopDestinations.CompletedTasksRoute.routeName)
                    }
                )
            }
        )
        composable(
            route = TopDestinations.MainRoute.routeName,
            content = {
                MainScreen(windowWidthSizeClass = windowWidthSizeClass)
            }
        )
        composable(
            route = TopDestinations.CompletedTasksRoute.routeName,
            content = { CompletedTasksScreen() }
        )
    }
}