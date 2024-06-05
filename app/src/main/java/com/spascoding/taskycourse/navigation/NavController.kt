package com.spascoding.taskycourse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spascoding.taskycourse.feature_register_screen.presentation.login.LoginScreen
import com.spascoding.taskycourse.feature_register_screen.presentation.register.RegisterScreen

@Composable
fun NavController(
) {
    val navController = rememberNavController()
    val startDestination = Navigation.LoginNavigation.route //TODO show AgendaScreen if user is logged in
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Navigation.LoginNavigation.route) {
            LoginScreen(navController)
        }
        composable(route = Navigation.RegisterNavigation.route) {
            RegisterScreen(navController)
        }
    }
}