package com.example.moviediscoveryapp.ui.details

import com.example.moviediscoveryapp.data.model.TitleDetailsResponse

sealed class DetailsUiState {

    object Loading : DetailsUiState()

    data class Success(
        val data: TitleDetailsResponse
    ) : DetailsUiState()

    data class Error(
        val message: String
    ) : DetailsUiState()
}
