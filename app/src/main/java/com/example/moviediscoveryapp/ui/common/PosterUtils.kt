package com.example.moviediscoveryapp.ui.common

fun resolvePoster(poster: String?): String? {
    if (poster.isNullOrBlank()) return null

    return if (poster.startsWith("http")) {
        poster
    } else {
        "https://cdn.watchmode.com/posters/$poster"
    }
}
