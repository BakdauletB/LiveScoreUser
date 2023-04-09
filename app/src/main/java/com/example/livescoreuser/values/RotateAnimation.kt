package com.example.livescoresdu.uilibrary.values

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
fun rotateCircle(): Float {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        )
    )
    return angle
}

@Composable
fun rotate(to: Float): Float {

    val angle: Float by animateFloatAsState(
        targetValue = to,
        animationSpec = tween(
            durationMillis = 2000, // duration
            easing = FastOutSlowInEasing
        )
    )


//    val infiniteTransition = rememberInfiniteTransition()
//    val angle by infiniteTransition.animateFloat(
//        initialValue = from,
//        targetValue = to,
//        animationSpec = infiniteRepeatable(
//            animation = tween(1000, easing = LinearEasing)
//        )
//    )
    return angle
}