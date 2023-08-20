package com.hotmail.or_dvir.televizia.data.mappers

import com.hotmail.or_dvir.televizia.data.local.models.MovieLocalModel
import com.hotmail.or_dvir.televizia.data.remote.models.MovieNetworkModel

fun MovieNetworkModel.toLocalModel(t:Int) = MovieLocalModel(
t
)
