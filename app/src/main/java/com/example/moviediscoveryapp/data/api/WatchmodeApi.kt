package com.example.moviediscoveryapp.data.api

import com.example.moviediscoveryapp.data.model.MovieResponse
import com.example.moviediscoveryapp.data.model.TitleDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WatchmodeApi {

    @GET("list-titles/")
    fun getMovies(
        @Query("apiKey") apiKey: String,
        @Query("types") type: String = "movie",
        @Query("regions") regions: String = "IN",
        @Query("page") page: Int = 1
    ): Single<MovieResponse>

    @GET("list-titles/")
    fun getTvShows(
        @Query("apiKey") apiKey: String,
        @Query("types") type: String = "tv_series",
        @Query("regions") regions: String = "IN",
        @Query("page") page: Int = 1
    ): Single<MovieResponse>

    @GET("title/{id}/details/")
    fun getTitleDetails(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): Single<TitleDetailsResponse>
}
