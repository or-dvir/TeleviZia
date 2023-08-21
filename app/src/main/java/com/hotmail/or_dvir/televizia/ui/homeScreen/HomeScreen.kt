package com.hotmail.or_dvir.televizia.ui.homeScreen

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MovieGrid(movies: List<MovieLocalModel>) {
    val itemSpacing = 8.dp
    val orientation = LocalConfiguration.current.orientation

    val minColumnWidth = if (orientation == ORIENTATION_PORTRAIT) 100.dp else 125.dp

    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(itemSpacing),
        columns = StaggeredGridCells.Adaptive(minColumnWidth),
        verticalItemSpacing = itemSpacing,
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(
            //todo when you have id's add them as key. MUST BE SAVEABLE IN A BUNDLE!
//            key = { it.id },
            items = movies
        ) {
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
                modifier = Modifier
                    .padding(8.dp)
                    .border(1.dp, Color.Blue),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    text = movie.name
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                    text = movie.releaseYear,
                    fontWeight = FontWeight.Bold
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
