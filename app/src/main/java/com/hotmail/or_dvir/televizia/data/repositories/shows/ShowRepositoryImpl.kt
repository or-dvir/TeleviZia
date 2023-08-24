package com.hotmail.or_dvir.televizia.data.repositories.shows

import com.hotmail.or_dvir.televizia.data.local.models.ShowLocalModel
import com.hotmail.or_dvir.televizia.data.mappers.toLocalModels
import com.hotmail.or_dvir.televizia.data.remote.models.ShowNetworkModel
import com.hotmail.or_dvir.televizia.ui.isEven
import kotlin.random.Random
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ShowRepositoryImpl : ShowRepository {
    // todo add scope that should not be cancelled.
    //      should be available everywhere

    //todo temp for testing
    private val allShows = List(9) { i: Int ->
        ShowNetworkModel(
            id = Random.nextInt(),
            name = StringBuilder().apply {
                repeat(Random.nextInt(1, 8)) {
                    append("show $i")
                    if (i != 9) {
                        append(" ")
                    }
                }
            }.toString(),
            releaseYear = "200$i",
            endYear = if (i.isEven()) null else "2010",
            posterUrl = "https://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg"
        )
    }

    override suspend fun getAllShows(): List<ShowLocalModel> {
        return withContext(Dispatchers.IO) {
            //todo temp for testing
            delay(3000)
            allShows.toLocalModels()
        }
    }
}
