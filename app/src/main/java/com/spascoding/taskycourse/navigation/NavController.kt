package com.spascoding.taskycourse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spascoding.taskycourse.feature_agenda_screen.presentation.agenda.AgendaScreen
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfo
import com.spascoding.taskycourse.feature_register_screen.data.local.model.userInfo
import com.spascoding.taskycourse.feature_register_screen.presentation.login.LoginScreen
import com.spascoding.taskycourse.feature_register_screen.presentation.register.RegisterScreen

@Composable
fun NavController(
) {
    val navController = rememberNavController()
    var startDestination = Navigation.LoginNavigation.route //TODO show AgendaScreen if user is logged in
    if (userInfo != UserInfo()) {
        startDestination = Navigation.AgendaNavigation.route
    }
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
        composable(route = Navigation.AgendaNavigation.route) {
            AgendaScreen(navController)
        }
    }
}