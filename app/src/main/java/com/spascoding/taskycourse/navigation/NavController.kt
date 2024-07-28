package com.spascoding.taskycourse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.spascoding.taskycourse.feature_agenda.presentation.agenda.AgendaScreenRoot
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.DetailScreenRoot
import com.spascoding.taskycourse.feature_auth.presentation.login.LoginScreenRoot
import com.spascoding.taskycourse.feature_auth.presentation.register.RegisterScreenRoot

@Composable
fun NavController(
    isAuthenticated: Boolean,
) {
    val navController = rememberNavController()

    val startDestination = if (isAuthenticated) {
        Navigation.AgendaNavigation.route
    } else {
        Navigation.LoginNavigation.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Navigation.LoginNavigation.route) {
            LoginScreenRoot(navController)
        }
        composable(route = Navigation.RegisterNavigation.route) {
            RegisterScreenRoot(navController)
        }
        composable(route = Navigation.AgendaNavigation.route) {
            AgendaScreenRoot(navController)
        }
        composable(
            route = Navigation.EventDetailNavigation.route + "?navigation={navigation}",
            arguments = listOf(navArgument("navigation") { type = NavType.StringType })
        ) {
            DetailScreenRoot(navController, Navigation.EventDetailNavigation)
        }
        composable(
            route = Navigation.TaskDetailNavigation.route + "?navigation={navigation}",
            arguments = listOf(navArgument("navigation") { type = NavType.StringType })
        ) {
            DetailScreenRoot(navController, Navigation.TaskDetailNavigation)
        }
        composable(
            route = Navigation.RemainderDetailNavigation.route + "?navigation={navigation}",
            arguments = listOf(navArgument("navigation") { type = NavType.StringType })
        ) {
            DetailScreenRoot(navController, Navigation.RemainderDetailNavigation)
        }
    }
}