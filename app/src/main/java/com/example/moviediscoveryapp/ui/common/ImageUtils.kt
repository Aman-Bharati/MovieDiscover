package com.example.moviediscoveryapp.ui.common

fun posterUrl(posterPath: String?): String? {
    return posterPath?.let {
        "https://images.watchmode.com/posters/$it"
    }
}
