package com.hotmail.or_dvir.televizia.data.remote.models

data class ShowNetworkModel(
    val id: Int,
    val name: String,
    val releaseYear: String,
    val endYear: String?,
    val posterUrl: String
)
