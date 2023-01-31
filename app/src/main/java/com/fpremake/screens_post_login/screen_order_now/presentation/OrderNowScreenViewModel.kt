package com.fpremake.screens_post_login.screen_order_now.presentation

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fpremake.shared.FPRemakeApplication
import com.fpremake.shared.data.meme_repository.MemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderNowScreenViewModel @Inject constructor(
    private val memeRepository: MemeRepository
) : AndroidViewModel(FPRemakeApplication.getInstance()) {

    private val _memesViewState = MutableStateFlow(OrderNowViewState())
    val memesViewState = _memesViewState.asStateFlow()

    init {
        fetchMemes()
    }

    private fun fetchMemes() {
        viewModelScope.launch {
            memeRepository.getMemes()
                .onStart {
                    _memesViewState.value = _memesViewState.value.copy(state = PageState.Loading)
                }
                .onCompletion {
                    _memesViewState.value = _memesViewState.value.copy(state = PageState.Content)
                }
                .collect {
                    _memesViewState.value = _memesViewState.value.copy(items = it)
                }
        }
    }
}