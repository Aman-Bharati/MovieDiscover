package com.example.moviediscoveryapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieTvItem(
    val id: Int,
    val title: String,
    val year: Int,

    // ✅ Watchmode list API already provides FULL URL
    @SerializedName("poster")
    val poster: String?
)
