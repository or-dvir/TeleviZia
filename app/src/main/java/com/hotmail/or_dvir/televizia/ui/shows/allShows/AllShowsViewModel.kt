package com.hotmail.or_dvir.televizia.ui.shows.allShows

import androidx.lifecycle.ViewModel
import com.hotmail.or_dvir.televizia.data.repositories.shows.ShowRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AllShowsViewModel(
    private val showsRepo: ShowRepository
) : ViewModel() {
    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

}
