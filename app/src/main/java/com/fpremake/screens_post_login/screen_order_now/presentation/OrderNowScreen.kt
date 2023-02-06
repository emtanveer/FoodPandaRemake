package com.fpremake.screens_post_login.screen_order_now.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import com.fpremake.screens_post_login.screen_order_now.data.MemeDetailEntity
import com.fpremake.utils.MyCustomLoader
import com.fpremake.utils.network_utils.ConnectionState
import com.fpremake.utils.network_utils.observeConnectivityAsFlow


//@Composable
//fun OrderNowScreen(navController: NavHostController?, viewModel: OrderNowScreenViewModel) {
//    val viewState by viewModel.memesViewState.collectAsState()
//    Log.d("TAG", "OrderNowScreen: $viewState")
//    OrderNowScreenUIContent(viewState)
//}
//
//@Composable
//fun HandleMemeApiResponse(viewState: OrderNowViewState) {
//    when (viewState.state) {
//        PageState.Content -> {
//            ShouldHandleMemeListPopulation(viewState = viewState)
//        }
//        PageState.Loading -> {
//            MyCustomLoader().LoadingScreen()
//        }
//        PageState.Error -> {
//            MyCustomLoader().LoadingScreen()
//        }
//    }
//}
//
//@Composable
//fun OrderNowScreenUIContent(viewState: OrderNowViewState) {
//    Surface(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Column(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 30.dp)
//            ) {
//                Text(
//                    modifier = Modifier.fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    text = "Data from API to match:",
//                    fontSize = 24.sp
//                )
//            }
//
//            //Creation and handling for Api Data receiving and populating List
//            HandleMemeApiResponse(viewState = viewState)
//        }
//
//    }
//}
//
//@Composable
//private fun ShouldHandleMemeListPopulation(viewState: OrderNowViewState,viewModel: OrderNowScreenViewModel = hiltViewModel()) {
//    val memeList = viewState.items.data?.memes
//    val lifecycle = LocalLifecycleOwner.current.lifecycle
//    val context = LocalContext.current
//
//    val connected by context.observeConnectivityAsFlow().collectAsState(initial = ConnectionState.Available)
//
//    when (connected) {
//        ConnectionState.Available -> {
//            viewModel.fetchMemes()
//            MemeListView(memeList)
//        }
//        ConnectionState.UnAvailable -> MemeListView(memeList)
//    }
//
//
//    //TODO: from entity to our data model class
//
//
//}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderNowScreen(
    navController: NavHostController?,
    viewModel: OrderNowScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current.lifecycle
    val connectionState by context.observeConnectivityAsFlow()
        .collectAsState(initial = ConnectionState.Available)
    val viewState by viewModel.memesViewState.collectAsState()
    val memeList = viewState.items?.data?.memes
    val isLoading by viewModel.isLoading.collectAsState()

    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    Log.e("TAG", "OrderNowScreen: ON_CREATE")
                }
                Lifecycle.Event.ON_START -> {
                    Log.e("TAG", "OrderNowScreen: ON_START")
                }
                Lifecycle.Event.ON_RESUME -> {
                    if (connectionState == ConnectionState.Available) {
                        Log.e("TAG", "OrderNowScreen: On_RESUME")
                        viewModel.fetchMemes()
                    }
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.e("TAG", "OrderNowScreen: ON_PAUSE")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.e("TAG", "OrderNowScreen: ON_STOP")
                }
                Lifecycle.Event.ON_DESTROY -> {
                    Log.e("TAG", "OrderNowScreen: ON_DESTROY")
                }
                Lifecycle.Event.ON_ANY -> {
                    Log.e("TAG", "OrderNowScreen: ON_ANY")
                }
            }
        }
        lifeCycleOwner.addObserver(observer)
        onDispose {
            lifeCycleOwner.removeObserver(observer)
        }
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isLoading,
        onRefresh = {
            viewModel.refresh()
        }
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        when (connectionState) {
            ConnectionState.Available -> {
                when (viewState.state) {
                    PageState.Content -> {
                       memeList?.let {
                            OrderNowScreenUIContent(memeList)
                        } ?: viewModel.fetchMemes()
                    }
                    PageState.Loading -> {
                        MyCustomLoader().LoadingScreen()
                    }
                    PageState.Error -> {
                        MyCustomLoader().LoadingScreen()
                    }
                }
           }
            ConnectionState.UnAvailable -> OrderNowScreenUIContent(memeList)
        }
       PullRefreshIndicator(isLoading, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun OrderNowScreenUIContent(memeList: ArrayList<MemeDetailEntity>?) {
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

            //Creation and handling for Api Data receiving and populating List
            //HandleMemeApiResponse()
            MemeListView(memeList = memeList)
        }

    }
}

@Composable
fun MemeListView(memeList: ArrayList<MemeDetailEntity>?) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            items = memeList?.toList() ?: emptyList(),
            key = { item ->
                item.id.let { itemId ->
                    itemId!!
                }
            },
            itemContent = {
                CardItemsViewState(meme = it)
            }
        )
    }
}



