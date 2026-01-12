package com.example.moviediscoveryapp.di

import com.example.moviediscoveryapp.data.api.WatchmodeApi
import com.example.moviediscoveryapp.data.repository.MovieRepository
import com.example.moviediscoveryapp.ui.details.DetailsViewModel
import com.example.moviediscoveryapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.watchmode.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(WatchmodeApi::class.java)
    }

    single { MovieRepository(get()) }

    viewModel { HomeViewModel(get()) }

    viewModel { DetailsViewModel(get()) }

}

