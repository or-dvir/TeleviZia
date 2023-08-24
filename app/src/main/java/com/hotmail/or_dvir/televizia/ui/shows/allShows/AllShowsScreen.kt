package com.hotmail.or_dvir.televizia.ui.shows.allShows

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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.hotmail.or_dvir.televizia.R
import com.hotmail.or_dvir.televizia.data.local.models.ShowLocalModel
import com.hotmail.or_dvir.televizia.ui.isEven
import com.hotmail.or_dvir.televizia.ui.shared.shimmerEffect
import com.hotmail.or_dvir.televizia.ui.shows.allShows.AllShowsUserActions.OnShowClicked
import com.hotmail.or_dvir.televizia.ui.shows.destinations.ShowDetailsScreenDestination
import com.hotmail.or_dvir.televizia.ui.shows.getShowPosterSize
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

// todo
//  add pagination

private typealias onUserAction = (AllShowsUserActions) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun AllShowsScreen(
    navigator: DestinationsNavigator,
    viewModel: AllShowsViewModel = koinViewModel()
) {
    val isLoading by viewModel.loadingState.collectAsStateWithLifecycle()
    val allShows by viewModel.allShows.collectAsStateWithLifecycle()

    val topAppBarBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(topAppBarBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.allShowsScreen_title)) },
                scrollBehavior = topAppBarBehavior
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                LoadingItemGrid()
            } else {
                ShowsGrid(allShows) { action ->
                    if (action is OnShowClicked) {
                        navigator.navigate(ShowDetailsScreenDestination(action.showId))
                    }
                    // todo more actions? pass along to view model
                }
            }
        }
    }
}

@Composable
private fun LoadingItem(nameSize: LoadingItemNameSize) {
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
                    .size(containerWidth, getShowPosterSize().second)
                    .shimmerEffect("ShowLoadingItemPoster")
            )
            Spacer(Modifier.height(5.dp))

            //region represents show name
            val lineHeight = 8.dp
            val lastLine = nameSize.numLines
            (1..lastLine).forEach { currentLine ->
                val lineWidthPercent = when (currentLine) {
                    1 -> 0.8f
                    lastLine -> 0.45f
                    else -> if (currentLine.isEven()) 0.65f else 0.55f
                }

                Box(
                    modifier = Modifier
                        .size(containerWidth * lineWidthPercent, lineHeight)
                        .shimmerEffect("ShowLoadingItemName"),
                )

                if (currentLine != lastLine) {
                    Spacer(Modifier.height(2.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            //endregion

            //represents show release/end year
            val yearWidth = remember(containerWidth) { containerWidth * 0.3f }
            Box(
                Modifier
                    .size(yearWidth, lineHeight)
                    .shimmerEffect("ShowLoadingItemYears")
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LoadingItemGrid() {
    val itemSpacing = 8.dp

    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        userScrollEnabled = false,
        contentPadding = PaddingValues(itemSpacing),
        verticalItemSpacing = itemSpacing,
        horizontalArrangement = Arrangement.spacedBy(itemSpacing),
        columns = StaggeredGridCells.Adaptive(getShowPosterSize().first)
    ) {
        items(15) { LoadingItem(LoadingItemNameSize.values().random()) }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingItemGridPreview() = LoadingItemGrid()

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowsGrid(
    shows: List<ShowLocalModel>,
    onUserAction: onUserAction
) {
    val itemSpacing = 8.dp

    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(itemSpacing),
        columns = StaggeredGridCells.Adaptive(getShowPosterSize().first),
        verticalItemSpacing = itemSpacing,
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(
            key = { it.id },
            items = shows
        ) {
            ShowListItem(it, onUserAction)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowsGridPreview() {
    val shortShowNames = List(4) { ShowLocalModel.dummyShow() }
    val longShowNames = List(4) { i ->
        ShowLocalModel.dummyShow(
            name = "a long name which spans multiple lines",
            endYear = if (i.isEven()) "2005" else null
        )
    }

    val result = shortShowNames.zip(longShowNames).flatMap {
        listOf(it.first, it.second)
    }

    ShowsGrid(result) { /*do nothing*/ }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowListItem(
    show: ShowLocalModel,
    onUserAction: onUserAction
) {
    Card(onClick = { onUserAction(OnShowClicked(show.id)) }) {
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
                    style = MaterialTheme.typography.titleMedium,
                    text = show.name
                )
                Spacer(modifier = Modifier.height(6.dp))

                val endYear = show.endYear ?: stringResource(R.string.present)
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    text = "${show.releaseYear}-$endYear",
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
        ShowListItem(ShowLocalModel.dummyShow()) { /*do nothing*/ }
        ShowListItem(
            ShowLocalModel.dummyShow(
                name = "a very long show name which spans multiple lines",
                endYear = null
            )
        ) { /*do nothing*/ }
    }
}


private enum class LoadingItemNameSize(val numLines: Int) {
    SHORT(1),
    MEDIUM(2),
    LONG(4),
    EXTRA_LONG(6)
}