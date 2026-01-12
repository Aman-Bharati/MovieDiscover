package com.example.moviediscoveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moviediscoveryapp.navigation.AppNavGraph
import com.example.moviediscoveryapp.ui.theme.MovieDiscoveryAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieDiscoveryAppTheme {

                AppNavGraph(
                    apiKey = "YOUR_API_KEY"
                )
            }
        }
    }
}
