package com.hotmail.or_dvir.televizia.ui.homeScreen

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hotmail.or_dvir.televizia.R
import com.hotmail.or_dvir.televizia.data.local.models.MovieLocalModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    Text("home")
}

@Composable
private fun MovieGrid(movies: List<MovieLocalModel>) {
    val itemSpacing = 8.dp
    val orientation = LocalConfiguration.current.orientation

    val minColumnWidth = if (orientation == ORIENTATION_PORTRAIT) 100.dp else 125.dp

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(itemSpacing),
        columns = GridCells.Adaptive(minColumnWidth),
        verticalArrangement = Arrangement.spacedBy(itemSpacing),
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(movies) {
            MovieListItem(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieGridPreview() {
    val shortMovieNames = List(4) { MovieLocalModel.dummyMovie() }
    val longMovieNames = List(4) {
        MovieLocalModel.dummyMovie(name = "a long name which spans multiple lines")
    }

    val result = shortMovieNames.zip(longMovieNames).flatMap {
        listOf(it.first, it.second)
    }

    MovieGrid(result)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieListItem(movie: MovieLocalModel) {
    Card(onClick = { /*TODO*/ }) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .focusable(false)
                    .fillMaxWidth(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(R.drawable.placeholder),
                model = movie.posterUrl
            )

            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    text = movie.name
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    text = movie.releaseYear
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListItemPreview() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MovieListItem(MovieLocalModel.dummyMovie())
        MovieListItem(MovieLocalModel.dummyMovie("a very long movie name which spands multiple lines"))
    }
}
