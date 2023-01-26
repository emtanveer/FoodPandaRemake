package com.fpremake.screens_post_login.screen_order_now.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material3.Text

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.fpremake.utils.MyCustomLoader


@Composable
fun OrderNowScreen(navController: NavHostController?, viewModel: OrderNowScreenViewModel) {
    OrderNowScreenUIContent()

    val viewState by viewModel.memesViewState.collectAsState()
    HandleMemeApiResponse(viewState = viewState)
}

@Composable
fun HandleMemeApiResponse(viewState: OrderNowViewState) {
    when (viewState.state) {
        PageState.Content -> {
            ShouldHandleMemeListPopulation(viewState = viewState)
        }
        PageState.Loading -> {
            MyCustomLoader().LoadingScreen()
        }
        PageState.Error -> {
            MyCustomLoader().LoadingScreen()
        }
    }
}

@Composable
fun OrderNowScreenUIContent() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Data from API to match:",
                    fontSize = 24.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Two Buttons",
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
private fun ShouldHandleMemeListPopulation(viewState: OrderNowViewState) {
    val memeList = viewState.items.data?.memes

    if (memeList?.size == 0) {
        //TODO()
        // To handle exception case here.
    } else {
        LazyColumn() {
            items(
                items = memeList.let { memes ->
                    memes!!.toList()
                },
                key = { item -> item.id.let { itemId -> itemId!! } },
                itemContent = {
                    CardItemsViewState(meme = it)
                }
            )
        }
    }
}



