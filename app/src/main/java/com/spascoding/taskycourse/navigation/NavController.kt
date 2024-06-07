package com.spascoding.taskycourse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spascoding.taskycourse.feature_agenda_screen.presentation.agenda.AgendaScreen
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfo
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfoManager
import com.spascoding.taskycourse.feature_register_screen.presentation.login.LoginScreen
import com.spascoding.taskycourse.feature_register_screen.presentation.register.RegisterScreen
import kotlinx.coroutines.launch

@Composable
fun NavController(
    userInfoManager: UserInfoManager
) {
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf(Navigation.LoginNavigation.route) }

    // Create a coroutine scope for reading the DataStore data
    val coroutineScope = rememberCoroutineScope()

    // Use LaunchedEffect to read the UserInfo from DataStore when the composable is first launched
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            userInfoManager.userInfoFlow.collect { userInfo ->
                startDestination = if (userInfo != UserInfo()) {
                    Navigation.AgendaNavigation.route
                } else {
                    Navigation.LoginNavigation.route
                }
            }
        }
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