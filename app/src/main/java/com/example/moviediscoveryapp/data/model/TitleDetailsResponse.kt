package com.example.moviediscoveryapp.data.model

data class TitleDetailsResponse(
    val id: Int,
    val title: String,
    val plot_overview: String?,
    val year: Int,

    //  POSTER FIELDS
    val poster: String?,
    val posterMedium: String?,
    val posterLarge: String?,
    val backdrop: String?
)
