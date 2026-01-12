package com.example.moviediscoveryapp.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.moviediscoveryapp.data.model.MovieTvItem
import com.example.moviediscoveryapp.ui.common.ShimmerMovieCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    apiKey: String,
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }

    // ✅ Prevent recomposition reloads
    LaunchedEffect(Unit) {
        viewModel.loadMoviesAndTv(apiKey)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Movies") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("TV Shows") }
                )
            }

            when (uiState) {

                is HomeUiState.Loading -> {
                    LoadingShimmer()
                }

                is HomeUiState.Success -> {
                    val data = uiState as HomeUiState.Success
                    val list =
                        if (selectedTab == 0) data.movies else data.tvShows

                    MovieList(list, navController)
                }

                is HomeUiState.Error -> {
                    val errorMessage = (uiState as HomeUiState.Error).message

                    LaunchedEffect(errorMessage) {
                        val result = snackbarHostState.showSnackbar(
                            message = errorMessage,
                            actionLabel = "Retry"
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.loadMoviesAndTv(apiKey)
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Something went wrong")
                    }
                }
            }
        }
    }
}

/* ---------- LIST ---------- */

@Composable
fun MovieList(
    items: List<MovieTvItem>,
    navController: NavHostController
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            MovieCard(item, navController)
        }
    }
}

/* ---------- CARD ---------- */

@Composable
fun MovieCard(
    item: MovieTvItem,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("details/${item.id}")
            },
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // ✅ Small image for list (PERFORMANCE FIX)
            Image(
                painter = rememberAsyncImagePainter(item.poster),
                contentDescription = item.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )




            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = item.year.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

/* ---------- SHIMMER ---------- */

@Composable
fun LoadingShimmer() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(6) {
            ShimmerMovieCard()
        }
    }

}

