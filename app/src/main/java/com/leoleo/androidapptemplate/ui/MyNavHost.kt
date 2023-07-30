package com.leoleo.androidapptemplate.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leoleo.androidapptemplate.ui.quiz.completed.CompletedQuizScreen

@Composable
internal fun MyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TopDestinations.MainRoute.routeName,
    windowWidthSizeClass: WindowWidthSizeClass,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = TopDestinations.MainRoute.routeName,
            content = {
                MainScreen(windowWidthSizeClass = windowWidthSizeClass, navigateToNextScreen = {
                    navController.navigate(TopDestinations.CompletedQuizRoute.routeName)
                })
            }
        )
        composable(
            route = TopDestinations.CompletedQuizRoute.routeName,
            content = { CompletedQuizScreen() }
        )
    }
}