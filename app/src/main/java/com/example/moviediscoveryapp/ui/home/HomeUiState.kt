package com.example.moviediscoveryapp.ui.home

import com.example.moviediscoveryapp.data.model.MovieTvItem

sealed class HomeUiState {

    object Loading : HomeUiState()

    data class Success(
        val movies: List<MovieTvItem>,
        val tvShows: List<MovieTvItem>
    ) : HomeUiState()

    data class Error(
        val message: String
    ) : HomeUiState()
}
