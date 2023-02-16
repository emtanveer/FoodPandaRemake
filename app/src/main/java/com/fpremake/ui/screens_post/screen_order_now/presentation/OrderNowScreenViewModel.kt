package com.fpremake.ui.screens_post.screen_order_now.presentation

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fpremake.shared.FPRemakeApplication
import com.fpremake.ui.screens_post.screen_order_now.data.repository.MemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderNowScreenViewModel @Inject constructor(
    private val memeRepository: MemeRepository
) : AndroidViewModel(FPRemakeApplication.getInstance()) {

    private val _memesViewState = MutableStateFlow(OrderNowViewState())
    val memesViewState = _memesViewState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
      //  fetchMemes()
    }

    fun refresh() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000)
            fetchMemes()
            _isLoading.value = false
        }
    }

    fun fetchMemes() {
        viewModelScope.launch {
            memeRepository.getMemes()
                .onStart {
                    _memesViewState.value = _memesViewState.value.copy(state = PageState.Loading)
                    delay(5000L)
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