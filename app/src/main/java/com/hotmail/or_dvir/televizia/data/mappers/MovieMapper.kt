package com.hotmail.or_dvir.televizia.data.mappers

import com.hotmail.or_dvir.televizia.data.local.models.MovieLocalModel
import com.hotmail.or_dvir.televizia.data.remote.models.MovieNetworkModel

fun List<MovieNetworkModel>.toLocalModels() = map { it.toLocalModel() }
fun MovieNetworkModel.toLocalModel() = MovieLocalModel(
    name = this.name,
    releaseYear = this.releaseYear,
    posterUrl = this.posterUrl
)
