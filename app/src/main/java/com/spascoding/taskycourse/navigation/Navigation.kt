package com.spascoding.taskycourse.navigation

sealed class Navigation(val route: String) {
    data object LoginNavigation : Navigation("LoginScreen")
    data object RegisterNavigation : Navigation("RegisterScreen")
    data object TaskDetailNavigation : Navigation("TaskDetailScreen")
    data object EventDetailNavigation : Navigation("EventDetailScreen")
    data object RemainderDetailNavigation : Navigation("RemainderDetailScreen")
    data object AgendaNavigation : Navigation("AgendaScreen")
}