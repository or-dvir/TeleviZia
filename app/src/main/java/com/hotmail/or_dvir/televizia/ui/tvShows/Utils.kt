package com.hotmail.or_dvir.televizia.ui.tvShows

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @return Pair<Dp, Dp> where `first` is the width and `second` is the height
 */
@Composable
fun getTvShowPosterSize(): Pair<Dp, Dp> {
    val orientation = LocalConfiguration.current.orientation
    return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        //100 is the width of the TvPoster, scaled down to phone size
        //height is 100*1.475, which is the original aspect ratio from TvMaze (200x295 -> 1:1.475)
        100.dp to (147.5).dp
    } else {
        125.dp to (184.375).dp
    }
}

