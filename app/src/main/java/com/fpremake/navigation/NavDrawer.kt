package com.fpremake.navigation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
            .background(Color.White)) {

        NavigationDrawerItem(
            icon = {
                Icon(
                    painter = painterResource(id = drawable.ic_dashboard),
                    tint = Color.White,
                    contentDescription = "Splash",
                    modifier = Modifier.size(
                        width = 25.dp,
                        height = 25.dp
                    )
                )
            },
            label = { Text(text = "Splash", color = Color.White) },
            selected = currentRoute == "splash",
            onClick = {
                navController?.navigate("splash") {
                    //Helps in the backstack clearing when navigating
                    //from one screen to other, means no screen will
                    //stack when routing from drawer screen by below code.
                    launchSingleTop = true
                    popUpTo("splash") {
                        saveState = true
                    }
                }

                closeNavDrawer()
            },
            modifier = Modifier.background(Color.Black),
        )

        NavigationDrawerItem(
            icon = {
                Icon(
                    painter = painterResource(id = drawable.ic_invoices),
                    tint = Color.White,
                    contentDescription = "Dashboard",
                    modifier = Modifier.size(
                        width = 25.dp,
                        height = 25.dp
                    )
                )
            },
            label = { Text(text = "Dashboard", color = Color.White) },
            selected = currentRoute == "dashboard",
            onClick = {
                navController?.navigate("dashboard") {
                    //Helps in the backstack clearing when navigating
                    //from one screen to other, means no screen will
                    //stack when routing from drawer screen by below code.
                    launchSingleTop = true
                    popUpTo("dashboard") {
                        saveState = true
                    }
                }

                closeNavDrawer()
            },
            modifier = Modifier.background(Color.Black)
        )
    }

}


//region helper method for Custom Drawer Navigation
@Composable
private fun CustomDrawerNavigationItems(
    navController: NavHostController?,
    closeNavDrawer: () -> Unit
) {
    //The problem with the custom drawer item was the selection of the current clicked drawer option,
    //which was implemented in 'NavigationDrawerItem', for such UX i.e. keep the selection of the item of drawer
    //we needed to code for that part too.
    val navBackStackEntry by navController?.currentBackStackEntryAsState()!!
    val currentRoute = navBackStackEntry?.destination?.route

    DrawerMenuItem(
        painter = painterResource(id = drawable.ic_home),
        text = "Splash",
        onItemClick = {
            navController?.navigate("splash") {
                //Helps in the backstack clearing when navigating
                //from one screen to other, means no screen will
                //stack when routing from drawer screen by below code.
                launchSingleTop = true
                popUpTo("splash") {
                    saveState = true
                }
            }

            closeNavDrawer()
        }
    )
    DrawerMenuItem(
        painter = painterResource(id = drawable.ic_search),
        text = "Dashboard",
        onItemClick = {
            navController?.navigate("dashboard") {
                launchSingleTop = true
                popUpTo("dashboard") {
                    saveState = true
                }
            }
            closeNavDrawer()
        }
    )

}

@Composable
fun DrawerMenuItem(
    painter: Painter,
    text: String,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(painter = painter, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text)
    }

}
//endregion


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PreviewDefaultDrawerNavigationMainBodyItemsWithSelectionEnabled(
    closeNavDrawer: () -> Unit
) {
    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(id = drawable.ic_dashboard),
                tint = Color.White,
                contentDescription = "dashboard",
                modifier = Modifier.size(
                    width = 25.dp,
                    height = 25.dp
                )
            )
        },
        label = { Text(text = "Dashboard", color = Color.White) },
        selected = true,
        onClick = {
            closeNavDrawer()
        },
        modifier = Modifier.background(Color.Black)
    )

    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(id = drawable.ic_invoices),
                tint = Color.White,
                contentDescription = "invoices",
                modifier = Modifier.size(
                    width = 25.dp,
                    height = 25.dp
                )
            )
        },
        label = { Text(text = "Invoices", color = Color.White) },
        selected = false,
        onClick = {
            closeNavDrawer()
        },
        modifier = Modifier.background(Color.Black)
    )

    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(id = drawable.ic_taxes),
                tint = Color.White,
                contentDescription = "taxes",
                modifier = Modifier.size(
                    width = 25.dp,
                    height = 25.dp
                )
            )
        },
        label = { Text(text = "Taxes", color = Color.White) },
        selected = false,
        onClick = {
            closeNavDrawer()
        },
        modifier = Modifier.background(Color.Black)
    )

    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(id = drawable.ic_my_businesses),
                tint = Color.White,
                contentDescription = "my businesses",
                modifier = Modifier.size(
                    width = 25.dp,
                    height = 25.dp
                )
            )
        },
        label = { Text(text = "My Businesses", color = Color.White) },
        selected = false,
        onClick = {
            closeNavDrawer()
        },
        modifier = Modifier.background(Color.Black),
    )

    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(id = drawable.ic_customers),
                tint = Color.White,
                contentDescription = "customers",
                modifier = Modifier.size(
                    width = 25.dp,
                    height = 25.dp
                )
            )
        },
        label = { Text(text = "Customers", color = Color.White) },
        selected = false,
        onClick = {
            closeNavDrawer()
        },
        modifier = Modifier.background(Color.Black)
    )

    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(id = drawable.ic_logout),
                tint = Color.White,
                contentDescription = "logout",
                modifier = Modifier.size(
                    width = 25.dp,
                    height = 25.dp
                )
            )
        },
        label = { Text(text = "Logout", color = Color.White) },
        selected = false,
        onClick = {
            closeNavDrawer()
        },
        modifier = Modifier.background(Color.Black)
    )

}


/**
Preview Section
 */
@Preview(showBackground = true)
@Composable
fun DrawerHeaderPreview() {
    DrawerHeader()
}

@Preview(showBackground = true)
@Composable
fun DrawerBodyPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        PreviewDefaultDrawerNavigationMainBodyItemsWithSelectionEnabled(
            closeNavDrawer = {}
        )
    }
}