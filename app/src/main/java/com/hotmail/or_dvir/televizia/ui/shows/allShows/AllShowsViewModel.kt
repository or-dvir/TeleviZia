package com.hotmail.or_dvir.televizia.ui.shows.allShows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotmail.or_dvir.televizia.data.local.models.ShowLocalModel
import com.hotmail.or_dvir.televizia.data.repositories.shows.ShowRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllShowsViewModel(
    private val showsRepo: ShowRepository
) : ViewModel() {
    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    private val _allShows = MutableStateFlow(emptyList<ShowLocalModel>())
    val allShows = _allShows.asStateFlow()

    init {
        loadAllShows()
    }

    private fun loadAllShows() = viewModelScope.launch {
        _loadingState.emit(true)
        // todo for now assume success
        _allShows.emit(showsRepo.getAllShows())
        _loadingState.emit(false)
    }
}
