package com.hotmail.or_dvir.televizia.ui.tvShows.allShows

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hotmail.or_dvir.televizia.R
import com.hotmail.or_dvir.televizia.data.local.models.TvShowLocalModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

// todo
//  add pagination

@RootNavGraph(start = true)
@Destination
@Composable
fun AllShowsScreen(
    navigator: DestinationsNavigator,
    viewModel: AllShowsViewModel = koinViewModel()
) {
    Text("all shows")
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowsGrid(shows: List<TvShowLocalModel>) {
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
            items = shows
        ) {
            ShowListItem(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowsGridPreview() {
    val shortShowNames = List(4) { TvShowLocalModel.dummyShow() }
    val longShowNames = List(4) { i ->
        TvShowLocalModel.dummyShow(
            name = "a long name which spans multiple lines",
            endYear = if (i % 2 == 0) "2005" else null
        )
    }

    val result = shortShowNames.zip(longShowNames).flatMap {
        listOf(it.first, it.second)
    }

    ShowsGrid(result)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowListItem(show: TvShowLocalModel) {
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
                placeholder = painterResource(R.drawable.poster_placeholder),
                model = show.posterUrl
            )

            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    text = show.name
                )
                Spacer(modifier = Modifier.height(5.dp))

                //todo is "current" the best strign here?
                val endYear = show.endYear ?: stringResource(R.string.present)
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                    text = "${show.releaseYear}-$endYear",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowListItemPreview() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ShowListItem(TvShowLocalModel.dummyShow())
        ShowListItem(
            TvShowLocalModel.dummyShow(
                name = "a very long show name which spans multiple lines",
                endYear = null
            )
        )
    }
}
