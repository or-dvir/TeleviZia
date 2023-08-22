package com.hotmail.or_dvir.televizia.ui.tvShows.allShows

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hotmail.or_dvir.televizia.R
import com.hotmail.or_dvir.televizia.data.local.models.TvShowLocalModel
import com.hotmail.or_dvir.televizia.ui.shared.shimmerEffect
import com.hotmail.or_dvir.televizia.ui.tvShows.getTvShowPosterSize
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.random.Random
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

@Composable
private fun LoadingItem() {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val containerWidth = maxWidth

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            // represents show poster
            Box(
                modifier = Modifier
                    .size(containerWidth, getTvShowPosterSize().second)
                    .shimmerEffect("TvShowLoadingItemPoster")
            )
            Spacer(Modifier.height(5.dp))


//            val nameFirstLineWidth = remember(maxWidth) { maxWidth * 0.8f }
//            val nameSecondLineWidth = remember(maxWidth) { maxWidth * 0.65f }

            //region represents show name
            val maxLines = 5
            val textHeight = 8.dp
            repeat(Random.nextInt(1, maxLines)) { index ->
                val lineWidthPercent = Random.nextInt(40, 100)
                val finalLineWidth = containerWidth * (lineWidthPercent / 100f)
                Box(
                    modifier = Modifier
//                        .size(containerWidth * 0.5f, textHeight)
                        .size(finalLineWidth, textHeight)
//                        .size(nameFirstLineWidth, textHeight)
                        .shimmerEffect("TvShowLoadingItemName"),
                )
                if (index != maxLines - 1) {
                    Spacer(Modifier.height(2.dp))
                }
//                Box(
//                    Modifier
//                        .size(nameSecondLineWidth, textHeight)
//                        .shimmerEffect("TvShowLoadingItemName")
//                )
            }
            //endregion

            //represents show release/end year
            val yearWidth = remember(containerWidth) { containerWidth * 0.3f }

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                Modifier
                    .size(yearWidth, textHeight)
                    .shimmerEffect("TvShowLoadingItemYears")
            )
        }
    }
}

@Composable
private fun LoadingItemGrid() {
    val itemSpacing = 8.dp
    LazyVerticalGrid(
        userScrollEnabled = false,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(itemSpacing),
        verticalArrangement = Arrangement.spacedBy(itemSpacing),
        horizontalArrangement = Arrangement.spacedBy(itemSpacing),
        columns = GridCells.Adaptive(getTvShowPosterSize().first)
    ) {
        items(15) { LoadingItem() }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingItemGridPreview() = LoadingItemGrid()

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowsGrid(shows: List<TvShowLocalModel>) {
    val itemSpacing = 8.dp

    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(itemSpacing),
        columns = StaggeredGridCells.Adaptive(getTvShowPosterSize().first),
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
