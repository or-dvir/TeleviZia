package com.hotmail.or_dvir.televizia.data.repositories.shows

import com.hotmail.or_dvir.televizia.data.local.models.ShowLocalModel

interface ShowRepository {
    suspend fun getAllShows(): List<ShowLocalModel>
}
