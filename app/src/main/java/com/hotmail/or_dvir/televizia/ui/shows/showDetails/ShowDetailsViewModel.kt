package com.hotmail.or_dvir.televizia.ui.shows.showDetails

import androidx.lifecycle.viewModelScope
import com.hotmail.or_dvir.televizia.data.repositories.shows.ShowRepository
import com.hotmail.or_dvir.televizia.ui.shared.LoadingViewModel
import kotlinx.coroutines.launch

class ShowDetailsViewModel(
    private val showId: Int,
    private val showsRepo: ShowRepository
) : LoadingViewModel() {

    init {
        loadShowDetails()
    }

    private fun loadShowDetails() = viewModelScope.launch {
        setLoadingState(true)
        // todo
        //  for now assume success
        //  DO ME!!!
        setLoadingState(false)
    }
}
