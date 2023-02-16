package com.fpremake.ui.screens_post.screen_dashboard.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.reflect.KSuspendFunction0

@Composable
fun SignInModalBottomSheetContent(onDismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "This is my modal bottom sheet content")
        // Add any other composables you want to display in the modal bottom sheet
        Button(onClick = onDismiss) {
            Text(text = "Close")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDefaultSignInBottomSheet() {
    SignInModalBottomSheetContent({})
}

