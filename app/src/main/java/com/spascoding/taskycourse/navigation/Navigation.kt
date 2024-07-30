package com.spascoding.taskycourse.navigation

sealed class Navigation(val route: String) {
    data object LoginNavigation : Navigation("LoginScreen")
    data object RegisterNavigation : Navigation("RegisterScreen")
    data object TaskDetailNavigation : Navigation("TaskDetailScreen")
    data object EventDetailNavigation : Navigation("EventDetailScreen")
    data object RemainderDetailNavigation : Navigation("RemainderDetailScreen")
    data object AgendaNavigation : Navigation("AgendaScreen")

    companion object {
        fun fromRoute(route: String): Navigation {
            return when (route) {
                LoginNavigation.route -> LoginNavigation
                RegisterNavigation.route -> RegisterNavigation
                TaskDetailNavigation.route -> TaskDetailNavigation
                EventDetailNavigation.route -> EventDetailNavigation
                RemainderDetailNavigation.route -> RemainderDetailNavigation
                AgendaNavigation.route -> AgendaNavigation
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
        }
    }
}