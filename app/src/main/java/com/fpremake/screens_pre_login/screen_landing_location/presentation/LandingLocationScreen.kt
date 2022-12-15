package com.fpremake.screens_pre_login.screen_landing_location.presentation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.fpremake.R
import com.fpremake.navigation.navigateAndReplaceStartRoute


@Composable
fun LandingLocationScreen(navController: NavHostController?) {
    LandingLocationUiContent(navController)
}


@Composable
fun LandingLocationUiContent(navController: NavHostController?) {

    var showFineLocationRationaleMessage by remember {
        mutableStateOf(false)
    }
    var showCoarseLocationRationaleMessage by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val activityContext = LocalContext.current as Activity

    val permission = arrayListOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            when {
                //getOrDefault work only in Api 24+
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    Log.d("TAG", "ACCESS_COARSE_LOCATION: Granted")
                    //Todo Approximate Permission Granted
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    Log.d("TAG", "ACCESS_FINE_LOCATION: Granted")
                    //Todo Precise Permission Granted
                }

                /**
                 * In an educational UI, explain to the user why your app requires this
                 * permission for a specific feature to behave as expected, and what
                 * features are disabled if it's declined. In this UI, include a
                 * "cancel" or "no thanks" button that lets the user continue
                 * using your app without granting the permission.
                 */
                ActivityCompat.shouldShowRequestPermissionRationale(activityContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION) -> { showCoarseLocationRationaleMessage = true }

                ActivityCompat.shouldShowRequestPermissionRationale(activityContext,
                    Manifest.permission.ACCESS_FINE_LOCATION) -> { showFineLocationRationaleMessage = true }

                else -> {
                    /**
                     * if both permission deny 2 time then it will not show again.
                     * then user need to manually allow this location permission for both,
                     * Precise and Approximate
                     */
                    Log.d("TAG", "No Permission")
                }
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier.weight(3F),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.location_logo),
                contentDescription = "location Logo",
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .background(Color.Transparent),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = "Find Restaurants and shops",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
            )
            Text(
                text = "near you!",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "By Allowing Location access, you can search for", fontSize = 16.sp)
            Text(text = "restaurants and shops near you and receive", fontSize = 16.sp)
            Text(text = "more accurate delivery.", fontSize = 16.sp)
        }

        Column(verticalArrangement = Arrangement.Bottom) {
            Button(modifier = Modifier
                .fillMaxWidth(),
                onClick = {
                    requestAndHandleLocationPermission(
                        context = context,
                        launcher = launcher,
                    )
                },
                shape = RoundedCornerShape(8.dp)) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Allow Location Access"
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(modifier = Modifier
                .fillMaxWidth(),
                onClick = {
                    navController?.navigateAndReplaceStartRoute("post-login")
                },
                shape = RoundedCornerShape(8.dp)) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Enter my location"
                )
            }
            if (showCoarseLocationRationaleMessage) {
                ShowAlertDialogForRationaleUIContent(
                    title = "Allow Approximate Location Permission",
                    message = "Allow Foodpanda to access Approximate location, this lets you see near by places, " +
                            "You can change this anytime in your device settings.",
                    permissions = permission.toTypedArray(),
                    launcher = launcher,
                    closeDialogCallback = { showCoarseLocationRationaleMessage = false }
                )
            }
            if (showFineLocationRationaleMessage) {
                ShowAlertDialogForRationaleUIContent(
                    title = "Allow Precise Location Permission",
                    message = "Allow Foodpanda to access Precise location, " +
                            "this will help you to see your current location & share your location with others, " +
                            "You can change this anytime in your device settings.",
                    permissions = permission.toTypedArray(),
                    launcher = launcher,
                    closeDialogCallback = { showFineLocationRationaleMessage = false }
                )
            }
        }
    }
}

/**
 * Show alert dialog
 *
 * @param modifier              Default Modifier.
 * @param title                 Title for alert dialog.
 * @param message               Message for alert dialog.
 * @param permissions           List of permissions for request.
 * @param launcher              Launcher for request permission when user deny permission .
 * @param closeDialogCallback   Callback when click on dialog action button & perform some operation, then close the dialog.
 */
@Composable
fun ShowAlertDialogForRationaleUIContent(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    permissions: Array<String>,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    closeDialogCallback: () -> Unit,
) {
    AlertDialog(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            TextButton(onClick = {
                launcher.launch(permissions)
                closeDialogCallback()
            }) {
                Text("Request")
            }
        },
        dismissButton = {
            TextButton(onClick = closeDialogCallback) {
                Text(text = "Cancel")
            }
        },
        onDismissRequest = {},
    )
}

private fun requestAndHandleLocationPermission(
    context: Context,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
) {
    val isFineLocationGranted = ContextCompat.checkSelfPermission(context,
        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    val isCoarseLocationGranted = ContextCompat.checkSelfPermission(context,
        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    val permissions = arrayListOf<String>()

    //if precise permission is not granted add permission for request
    if (!isFineLocationGranted) {
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
    }
    //if approximate permission is not granted add permission for request
    if (!isCoarseLocationGranted) {
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    if (permissions.isNotEmpty()) {
        launcher.launch(permissions.toTypedArray())
    }
}


@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun PreviewLandingLocation() {
    Surface(modifier = Modifier.fillMaxSize()) {
        LandingLocationUiContent(null)
    }
}

