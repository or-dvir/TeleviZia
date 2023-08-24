package com.hotmail.or_dvir.televizia.ui.shows.showDetails

import androidx.compose.runtime.Composable
import com.hotmail.or_dvir.televizia.ui.shows.allShows.AllShowsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

//todo
//  poster (landscape)
//  description
//  language
//  country
//  years active
//  where its showing (tv network/streaming)
//  photos (available in API?)
//  cast
//  crew?
//  episode runtime (is that available at this level of the API?)
//  num of seasons
//  total num of episodes
//  genres
//  ratings (imdb/rotten tomatoes/what else?)

@Destination
@Composable
fun ShowDetailsScreen(
    navigator: DestinationsNavigator,
    showId: Int,
    viewModel: AllShowsViewModel = koinViewModel(parameters = { parametersOf(showId) }),
) {
    stopped here

}


