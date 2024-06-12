package com.spascoding.taskycourse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spascoding.taskycourse.feature_agenda_screen.presentation.agenda.AgendaScreen
import com.spascoding.taskycourse.feature_register_screen.presentation.login.LoginScreenRoot
import com.spascoding.taskycourse.feature_register_screen.presentation.register.RegisterScreen

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
            RegisterScreen(navController)
        }
        composable(route = Navigation.AgendaNavigation.route) {
            AgendaScreen(navController)
        }
    }
}