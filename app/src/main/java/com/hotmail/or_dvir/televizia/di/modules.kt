package com.hotmail.or_dvir.televizia.di

import com.hotmail.or_dvir.televizia.data.repositories.movies.MovieRepository
import com.hotmail.or_dvir.televizia.data.repositories.movies.MovieRepositoryImpl
import com.hotmail.or_dvir.televizia.data.repositories.shows.ShowRepository
import com.hotmail.or_dvir.televizia.data.repositories.shows.ShowRepositoryImpl
import com.hotmail.or_dvir.televizia.ui.shows.allShows.AllShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val repositoriesModule = module {
    single<MovieRepository> { MovieRepositoryImpl() }
    single<ShowRepository> { ShowRepositoryImpl() }
}

internal val viewModelsModule = module {
    viewModel { AllShowsViewModel(get()) }
}