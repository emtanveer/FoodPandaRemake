package com.fpremake.navigation

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.fpremake.screens_post_login.screen_dashboard.presentation.DashboardScreen
import com.fpremake.screens_post_login.screen_dashboard.presentation.DashboardScreenViewModel
import com.fpremake.screens_pre_login.screen_landing_location.presentation.LandingLocationScreen
import com.fpremake.screens_pre_login.screen_user_location.presentation.UserLocationScreen
import com.fpremake.shared.viewmodel.base.FPRemakeViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = "screen_landing_location"
) {

    //region For Drawer purpose (some are shared i.e. navController and scope)
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    //endregion

    //region Root Navigation Graph
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
    //endregion
}

//region onBoardingGraph
fun NavGraphBuilder.onBoardingGraph(
    navController: NavHostController,
) {
    composable("screen_landing_location") {
        LandingLocationScreen(navController)
    }
}
//endregion

//region mainApplicationGraph
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun NavGraphBuilder.mainApplicationGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    navigation(startDestination = "screen_dashboard", route = "post-login") {
        composable("screen_dashboard") {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Dashboard")
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            })
                            {
                                Icon(Icons.Filled.Menu, "")
                            }
                        },
                    )
                },
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
                //var userRepository: UserRealmRepository
                // Here we instantiate our ViewModel leveraging delegates and
                // a trailing lambda
                val dashboardViewModel: DashboardScreenViewModel = viewModel(
                    factory = FPRemakeViewModelFactory()
                )

                DashboardScreen(dashboardViewModel,navController)
            }
        }

        //Todo Move to Onboard Flow, we just place this here for testing purpose
        composable("screen_user_location") {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Select Location")
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    navController.popBackStack()
                                }
                            })
                            {
                                Icon(Icons.Filled.ArrowBack, "")
                            }
                        },
                    )
                },
                scaffoldState = scaffoldState,
            ) {
                UserLocationScreen(navController)
            }
        }
    }
}
//endregion

