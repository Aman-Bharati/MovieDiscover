package com.example.moviediscoveryapp

import android.app.Application
import com.example.moviediscoveryapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
            modules(appModule)
        }
    }
}
