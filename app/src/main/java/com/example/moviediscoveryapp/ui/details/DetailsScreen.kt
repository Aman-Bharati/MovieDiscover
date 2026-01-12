package com.example.moviediscoveryapp.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviediscoveryapp.ui.common.DetailsShimmer
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    movieId: Int,
    apiKey: String,
    viewModel: DetailsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.loadDetails(movieId, apiKey)
    }

    Scaffold { paddingValues ->

        when (uiState) {

            is DetailsUiState.Loading -> {
                DetailsShimmer()
            }

            is DetailsUiState.Success -> {
                val data = (uiState as DetailsUiState.Success).data

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues) // ✅ FIX
                        .verticalScroll(rememberScrollState())
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(
                            model = data.posterLarge ?: data.poster
                        ),
                        contentDescription = data.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp), // better cinematic ratio
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = data.title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Text(
                        text = data.year.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = data.plot_overview ?: "No description available",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            is DetailsUiState.Error -> {

                val snackbarHostState = remember { SnackbarHostState() }

                LaunchedEffect(Unit) {
                    val result = snackbarHostState.showSnackbar(
                        message = "Failed to load details",
                        actionLabel = "Retry"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.loadDetails(movieId, apiKey)
                    }
                }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { padding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Error loading details")
                    }
                }
            }
        }
    }
}
