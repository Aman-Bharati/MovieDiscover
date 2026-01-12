package com.example.moviediscoveryapp.data.repository

import com.example.moviediscoveryapp.data.api.WatchmodeApi
import com.example.moviediscoveryapp.data.model.MovieTvItem
import com.example.moviediscoveryapp.data.model.TitleDetailsResponse
import io.reactivex.rxjava3.core.Single

class MovieRepository(
    private val api: WatchmodeApi
) {

    fun fetchMoviesAndTvShows(
        apiKey: String
    ): Single<Pair<List<MovieTvItem>, List<MovieTvItem>>> {

        return Single.zip(
            api.getMovies(apiKey),
            api.getTvShows(apiKey)
        ) { movieResponse, tvResponse ->

            Pair(
                movieResponse.titles.shuffled().take(20),
                tvResponse.titles.shuffled().take(20)
            )
        }
    }
    fun fetchTitleDetails(
        id: Int,
        apiKey: String
    ): Single<TitleDetailsResponse> {
        return api.getTitleDetails(id, apiKey)
    }

}
