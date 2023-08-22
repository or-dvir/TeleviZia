package com.hotmail.or_dvir.televizia.data.repositories.shows

import com.hotmail.or_dvir.televizia.data.local.models.TvShowLocalModel
import com.hotmail.or_dvir.televizia.data.mappers.toLocalModels
import com.hotmail.or_dvir.televizia.data.remote.models.TvShowNetworkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class TvShowRepositoryImpl : TvShowRepository {
    // todo add scope that should not be cancelled.
    //      should be available everywhere

    //todo temp for testing
    private val allShows = List(9) { i: Int ->
        TvShowNetworkModel(
            name = "show $i",
            releaseYear = "200$i",
            endYear = if (i % 2 == 0) null else "2010",
            posterUrl = "https://i.etsystatic.com/13367669/r/il/db21fd/2198543930/il_570xN.2198543930_4qne.jpg"
        )
    }

    override suspend fun getAllShows(): List<TvShowLocalModel> {
        return withContext(Dispatchers.IO) {
            //todo temp for testing
            delay(3000)
            allShows.toLocalModels()
        }
    }
}
