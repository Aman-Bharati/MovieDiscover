package com.example.moviediscoveryapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerMovieCard() {

    val brush = shimmerBrush()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(12.dp)
    ) {

        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(brush)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(180.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )

            Box(
                modifier = Modifier
                    .height(14.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
        }
    }
}
