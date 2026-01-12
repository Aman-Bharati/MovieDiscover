package com.example.moviediscoveryapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailsShimmer() {
    val brush = shimmerBrush()

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(brush)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .height(24.dp)
                .width(200.dp)
                .background(brush)
                .padding(16.dp)
        )
    }
}
