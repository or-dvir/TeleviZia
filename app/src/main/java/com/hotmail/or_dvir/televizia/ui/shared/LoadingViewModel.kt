package com.hotmail.or_dvir.televizia.ui.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class LoadingViewModel : ViewModel() {
    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    suspend fun setLoadingState(isLoading: Boolean) = _loadingState.emit(isLoading)
}
