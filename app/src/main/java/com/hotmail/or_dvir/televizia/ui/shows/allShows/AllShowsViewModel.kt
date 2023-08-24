package com.hotmail.or_dvir.televizia.ui.shows.allShows

import androidx.lifecycle.viewModelScope
import com.hotmail.or_dvir.televizia.data.local.models.ShowLocalModel
import com.hotmail.or_dvir.televizia.data.repositories.shows.ShowRepository
import com.hotmail.or_dvir.televizia.ui.shared.LoadingViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllShowsViewModel(
    private val showsRepo: ShowRepository
) : LoadingViewModel() {

    private val _allShows = MutableStateFlow(emptyList<ShowLocalModel>())
    val allShows = _allShows.asStateFlow()

    init {
        loadAllShows()
    }

    private fun loadAllShows() = viewModelScope.launch {
        setLoadingState(true)
        // todo for now assume success
        _allShows.emit(showsRepo.getAllShows())
        setLoadingState(false)
    }
}

sealed class AllShowsUserActions {
    data class OnShowClicked(val showId: Int) : AllShowsUserActions()
}
