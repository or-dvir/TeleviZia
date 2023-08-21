package com.hotmail.or_dvir.televizia.data.repositories

import com.hotmail.or_dvir.televizia.data.local.models.MovieLocalModel
import com.hotmail.or_dvir.televizia.data.mappers.toLocalModels
import com.hotmail.or_dvir.televizia.data.remote.models.MovieNetworkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MovieRepositoryImpl : MovieRepository {
    // todo add scope that should not be cancelled.
    //      should be available everywhere

    //todo temp for testing
    private val allMovies = List(9) { i: Int ->
        MovieNetworkModel(name = "movie $i", releaseYear = "200$i")
    }

    override suspend fun getAllMovies(): List<MovieLocalModel> {
        return withContext(Dispatchers.IO) {
            //todo temp for testing
            delay(3000)
            allMovies.toLocalModels()
        }
    }
}