package com.fpremake.navigation

import com.fpremake.R

sealed class NavDrawerItem(val route: String, val icon: Int, val title: String) {
    object Splash : NavDrawerItem(route = "splash", icon = R.drawable.ic_invoices, title = "Splash")
    object Dashboard : NavDrawerItem(route = "dashboard", icon = R.drawable.ic_dashboard, title = "DashBoard")
}