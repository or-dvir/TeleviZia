package com.hotmail.or_dvir.televizia.ui.shows.showDetails

import androidx.lifecycle.ViewModel
import com.hotmail.or_dvir.televizia.data.repositories.shows.ShowRepository

class ShowDetailsViewModel(
    private val showId: Int,
    private val showsRepo: ShowRepository
) : ViewModel() {


}
