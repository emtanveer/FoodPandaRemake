package com.fpremake.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fpremake.R.*

@Composable
fun DrawerHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Header", fontSize = 60.sp)
    }
}

@Composable
fun DrawerBody(navController: NavHostController?, closeNavDrawer: () -> Unit) {
    Column {
        DefaultDrawerNavigationItemsWithSelectionEnabled(navController, closeNavDrawer)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DefaultDrawerNavigationItemsWithSelectionEnabled(
    navController: NavHostController?,
    closeNavDrawer: () -> Unit,
) {
    val navBackStackEntry by navController?.currentBackStackEntryAsState()!!
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current
    val drawerItems = listOf(
        NavDrawerItem.Splash,
        NavDrawerItem.Dashboard,
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        drawerItems.forEach { item ->
            DrawerItem(item = item, selected = currentRoute == item.route, onItemClick = {
                navController?.navigate(item.route) {
                    //Helps in the backstack clearing when navigating
                    //from one screen to other, means no screen will
                    //stack when routing from drawer screen by below code.
                    launchSingleTop = true
                    popUpTo(item.route) {
                        saveState = true
                    }
                }
                closeNavDrawer()
            })
        }

//        NavigationDrawerItem(
//            icon = {
//                Icon(
//                    painter = painterResource(id = drawable.ic_dashboard),
//                    contentDescription = "Splash",
//                    modifier = Modifier.size(
//                        width = 25.dp,
//                        height = 25.dp
//                    )
//                )
//            },
//            label = { Text(text = "Splash") },
//            selected = currentRoute == "splash",
//            onClick = {
//                navController?.navigate("splash") {
//                    //Helps in the backstack clearing when navigating
//                    //from one screen to other, means no screen will
//                    //stack when routing from drawer screen by below code.
//                    launchSingleTop = true
//                    popUpTo("splash") {
//                        saveState = true
//                    }
//                }
//
//                closeNavDrawer()
//            },
//        )
//
//        NavigationDrawerItem(
//            icon = {
//                Icon(
//                    painter = painterResource(id = drawable.ic_invoices),
//                    contentDescription = "Dashboard",
//                    modifier = Modifier.size(
//                        width = 25.dp,
//                        height = 25.dp
//                    )
//                )
//            },
//            label = { Text(text = "Dashboard") },
//            selected = currentRoute == "dashboard",
//            onClick = {
//                navController?.navigate("dashboard") {
//                    //Helps in the backstack clearing when navigating
//                    //from one screen to other, means no screen will
//                    //stack when routing from drawer screen by below code.
//                    launchSingleTop = true
//                    popUpTo("dashboard") {
//                        saveState = true
//                    }
//                }
//
//                closeNavDrawer()
//            },
//        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItem(item: NavDrawerItem, selected: Boolean, onItemClick: (NavDrawerItem) -> Unit) {
    NavigationDrawerItem(
        label = { Text(text = item.title) },
        selected = selected,
        onClick = { onItemClick(item) },
        icon = {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.title,
                modifier = Modifier.size(
                    width = 25.dp,
                    height = 25.dp
                )
            )
        },
    )
}

/**
Preview Section
 */
@Preview(showBackground = true)
@Composable
fun PreviewDrawerHeader() {
    DrawerHeader()
}

@Preview(showBackground = true)
@Composable
fun PreviewDrawerBody() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        PreviewDefaultDrawerNavigationMainBodyItemsWithSelectionEnabled(
            closeNavDrawer = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PreviewDefaultDrawerNavigationMainBodyItemsWithSelectionEnabled(
    closeNavDrawer: () -> Unit,
) {
    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(id = drawable.ic_dashboard),
                contentDescription = "Splash",
                modifier = Modifier.size(
                    width = 25.dp,
                    height = 25.dp
                )
            )
        },
        label = { Text(text = "Splash") },
        selected = true,
        onClick = {
            closeNavDrawer()
        },
    )

    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(id = drawable.ic_invoices),
                contentDescription = "Dashboard",
                modifier = Modifier.size(
                    width = 25.dp,
                    height = 25.dp
                )
            )
        },
        label = { Text(text = "Dashboard") },
        selected = false,
        onClick = {
            closeNavDrawer()
        },
    )

}