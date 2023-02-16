package com.fpremake.navigation

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.fpremake.ui.screens_post.screen_dashboard.presentation.DashboardScreen
import com.fpremake.ui.screens_post.screen_dashboard.presentation.DashboardScreenViewModel
import com.fpremake.ui.screens_post.screen_dashboard.presentation.component.SignInModalBottomSheetContent
import com.fpremake.ui.screens_post.screen_order_now.presentation.OrderNowScreen
import com.fpremake.ui.screens_pre.screen_landing_location.presentation.LandingLocationScreen
import com.fpremake.ui.screens_pre.screen_user_location.presentation.UserLocationScreen

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
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

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    //region Root Navigation Graph
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        onBoardingGraph(
            navController = navController,
            scaffoldState = scaffoldState,
            scope = scope
        )

        mainApplicationGraph(
            navController = navController,
            scaffoldState = scaffoldState,
            scope = scope,
            modalBottomSheetState = modalBottomSheetState
        )
    }
    //endregion
}

//region onBoardingGraph
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun NavGraphBuilder.onBoardingGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
) {
    composable("screen_landing_location") {
        LandingLocationScreen(navController)
    }
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
//endregion

//region mainApplicationGraph
@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun NavGraphBuilder.mainApplicationGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState
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
                    DrawerBody(
                        navController = navController,
                        closeNavDrawer = {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        },
                        modalBottomSheetState = modalBottomSheetState
                    )
                },
            ) {
                ModalBottomSheetLayout(
                    sheetState = modalBottomSheetState,
                    sheetContent = {
                        SignInModalBottomSheetContent {
                            scope.launch {
                                modalBottomSheetState.hide()
                            }
                        }
                    }
                ) {
                    /* Define your main screen content here */
                    val viewModel = hiltViewModel<DashboardScreenViewModel>()
                    DashboardScreen(navController, viewModel)
                }
            }
        }

        composable("screen_order_now") {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Order Now")
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
                    DrawerBody(
                        navController = navController,
                        closeNavDrawer = {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        },
                        modalBottomSheetState = modalBottomSheetState
                    )
                },
            ) {
                OrderNowScreen(navController)
            }
        }
    }
}
//endregion

