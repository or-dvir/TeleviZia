package com.hotmail.or_dvir.televizia.data.mappers

import com.hotmail.or_dvir.televizia.data.local.models.TvShowLocalModel
import com.hotmail.or_dvir.televizia.data.remote.models.TvShowNetworkModel

fun TvShowNetworkModel.toLocalModel(t:Int) = TvShowLocalModel(
t
)