package com.fpremake.navigation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.DialogFragmentNavigator.Destination
import com.fpremake.screens_post_login.screen_dashboard.presentation.DashboardScreen
import com.fpremake.screens_pre_login.screen_splash.presentation.SplashScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = "splash"
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

        preLoginGraph(
            navController = navController,
//            scaffoldState = scaffoldState,
//            scope = scope
        )

        postLoginGraph(
            navController = navController,
            scaffoldState = scaffoldState,
            scope = scope
        )


    }

}

fun NavGraphBuilder.preLoginGraph(
    navController: NavHostController,
//    scaffoldState: ScaffoldState,
//    scope: CoroutineScope
) {
//    navigation(startDestination = "splash", route = "pre-login") {
        composable("splash") {
            SplashScreen(navController)
        }
//    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun NavGraphBuilder.postLoginGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {

    navigation(startDestination = "dashboard", route = "post-login") {
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

