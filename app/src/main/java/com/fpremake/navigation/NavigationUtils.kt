package com.fpremake.navigation

import androidx.navigation.NavHostController

// Extension function to handle routes navigation(switch b/w pre-login route and post-login route).
fun NavHostController.navigateAndReplaceStartRoute(newHomeRoute: String) {
    popBackStack(graph.startDestinationId, true)
    graph.setStartDestination(newHomeRoute)
    navigate(newHomeRoute)
}