package com.fpremake.navigation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.fpremake.screens_post_login.screen_dashboard.presentation.DashboardScreen
import com.fpremake.screens_pre_login.screen_landing_location.presentation.LandingLocationScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = "landing_location"
) {

    //region For Drawer purpose (some are shared i.e. navController and scope)
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    //endregion


    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        onBoardingGraph(navController = navController,)

        mainApplicationGraph(
            navController = navController,
            scaffoldState = scaffoldState,
            scope = scope
        )
    }
}

fun NavGraphBuilder.onBoardingGraph(
    navController: NavHostController,
) {
    composable("landing_location") {
        LandingLocationScreen(navController)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun NavGraphBuilder.mainApplicationGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {

    navigation(startDestination = "landing_location", route = "post-login") {
        composable("dashboard") {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = {
                    DrawerHeader()
                    DrawerBody(navController = navController, closeNavDrawer = {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    })
                },
            ) {
                DashboardScreen()
            }

        }
    }
}

