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
    closeNavDrawer: () -> Unit
) {
    val navBackStackEntry by navController?.currentBackStackEntryAsState()!!
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
          ) {

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
            label = { Text(text = "Dashboard",) },
            selected = currentRoute == "screen_dashboard",
            onClick = {
                navController?.navigate("screen_dashboard") {
                    //Helps in the backstack clearing when navigating
                    //from one screen to other, means no screen will
                    //stack when routing from drawer screen by below code.
                    launchSingleTop = true
                    popUpTo("screen_dashboard") {
                        saveState = true
                    }
                }

                closeNavDrawer()
            },
        )


        NavigationDrawerItem(
            icon = {
                Icon(
                    painter = painterResource(id = drawable.ic_dashboard),
                    contentDescription = "Location",
                    modifier = Modifier.size(
                        width = 25.dp,
                        height = 25.dp
                    )
                )
            },
            label = { Text(text = "Location") },
            selected = currentRoute == "screen_landing_location",
            onClick = {
                navController?.navigate("screen_landing_location") {
                    //Helps in the backstack clearing when navigating
                    //from one screen to other, means no screen will
                    //stack when routing from drawer screen by below code.
                    launchSingleTop = true
                    popUpTo("screen_landing_location") {
                        saveState = true
                    }
                }

                closeNavDrawer()
            },
        )
    }

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
    closeNavDrawer: () -> Unit
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
        label = { Text(text = "Dashboard",) },
        selected = false,
        onClick = {
            closeNavDrawer()
        },
    )

}