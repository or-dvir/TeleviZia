package com.hotmail.or_dvir.televizia.data.mappers

import com.hotmail.or_dvir.televizia.data.local.models.ShowLocalModel
import com.hotmail.or_dvir.televizia.data.remote.models.ShowNetworkModel

fun List<ShowNetworkModel>.toLocalModels() = map { it.toLocalModel() }
fun ShowNetworkModel.toLocalModel() = ShowLocalModel(
    id = this.id,
    name = this.name,
    releaseYear = this.releaseYear,
    endYear = this.endYear,
    posterUrl = this.posterUrl
)
