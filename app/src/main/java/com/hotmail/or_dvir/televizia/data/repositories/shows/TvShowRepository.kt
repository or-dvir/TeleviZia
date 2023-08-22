package com.hotmail.or_dvir.televizia.data.repositories.shows

import com.hotmail.or_dvir.televizia.data.local.models.TvShowLocalModel

interface TvShowRepository {
    suspend fun getAllShows(): List<TvShowLocalModel>
}
