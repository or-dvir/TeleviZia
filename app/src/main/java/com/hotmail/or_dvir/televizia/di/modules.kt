package com.hotmail.or_dvir.televizia.di

import com.hotmail.or_dvir.televizia.data.repositories.MovieRepository
import com.hotmail.or_dvir.televizia.data.repositories.MovieRepositoryImpl
import com.hotmail.or_dvir.televizia.ui.homeScreen.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val repositoriesModule = module {
    single<MovieRepository> { MovieRepositoryImpl() }
}

internal val viewModelsModule = module {
    viewModel { HomeScreenViewModel(get()) }
}