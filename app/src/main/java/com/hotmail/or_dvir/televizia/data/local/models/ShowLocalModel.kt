package com.hotmail.or_dvir.televizia.data.local.models

data class ShowLocalModel(
    val name: String,
    val releaseYear: String,
    val endYear: String?,
    val posterUrl: String
) {
    companion object {
        fun dummyShow(
            name: String = "test show",
            releaseYear: String = "2000",
            endYear: String? = "2010",
        ) = ShowLocalModel(
            name = name,
            releaseYear = releaseYear,
            endYear = endYear,
            posterUrl = "https://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg"
        )
    }
}
