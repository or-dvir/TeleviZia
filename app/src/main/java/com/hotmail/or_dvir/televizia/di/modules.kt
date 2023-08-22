package com.hotmail.or_dvir.televizia.di

import com.hotmail.or_dvir.televizia.data.repositories.movies.MovieRepository
import com.hotmail.or_dvir.televizia.data.repositories.movies.MovieRepositoryImpl
import com.hotmail.or_dvir.televizia.data.repositories.shows.TvShowRepository
import com.hotmail.or_dvir.televizia.data.repositories.shows.TvShowRepositoryImpl
import com.hotmail.or_dvir.televizia.ui.tvShows.allShows.AllShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val repositoriesModule = module {
    single<MovieRepository> { MovieRepositoryImpl() }
    single<TvShowRepository> { TvShowRepositoryImpl() }
}

internal val viewModelsModule = module {
    viewModel { AllShowsViewModel(get()) }
}