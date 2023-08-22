package com.hotmail.or_dvir.televizia.data.mappers

import com.hotmail.or_dvir.televizia.data.local.models.TvShowLocalModel
import com.hotmail.or_dvir.televizia.data.remote.models.TvShowNetworkModel

fun List<TvShowNetworkModel>.toLocalModels() = map { it.toLocalModel() }
fun TvShowNetworkModel.toLocalModel() = TvShowLocalModel(
    name = this.name,
    releaseYear = this.releaseYear,
    endYear = this.endYear,
    posterUrl = this.posterUrl
)
