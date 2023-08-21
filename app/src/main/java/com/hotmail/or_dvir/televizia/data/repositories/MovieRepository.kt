package com.hotmail.or_dvir.televizia.data.repositories

import com.hotmail.or_dvir.televizia.data.local.models.MovieLocalModel

interface MovieRepository {
    suspend fun getAllMovies(): List<MovieLocalModel>
}
