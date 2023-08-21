package com.hotmail.or_dvir.televizia.data.local.models

data class MovieLocalModel(
    val name: String,
    val releaseYear: String,
    val posterUrl: String,
) {
    companion object {
        fun dummyMovie(name: String = "test movie") = MovieLocalModel(
            name = name,
            releaseYear = "2000",
            posterUrl = "https://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg"
        )
    }
}
