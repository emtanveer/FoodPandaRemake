package com.fpremake.screens_post_login.screen_order_now.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fpremake.screens_post_login.screen_order_now.business.MemeDetail

@Composable
fun CardItemsViewState(meme: MemeDetail) {
    Row {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        )
        {
            Text(text = meme.name ?: "" , style = MaterialTheme.typography.h6)
            Text(text = "VIEW DETAIL", style = MaterialTheme.typography.caption)
        }
    }
}