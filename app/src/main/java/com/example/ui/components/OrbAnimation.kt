package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun OrbAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "orb_transition")
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "orb_scale"
    )
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "orb_alpha"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val radius = size.minDimension / 3 * scale
        
        drawCircle(
            color = Color(0xFF6200EA).copy(alpha = alpha),
            radius = radius + 20f,
            center = center
        )
        
        drawCircle(
            color = Color(0xFFBB86FC),
            radius = radius,
            center = center,
            style = Stroke(width = 4f)
        )
        
        drawCircle(
            color = Color(0xFF6200EA),
            radius = radius - 10f,
            center = center
        )
    }
}
