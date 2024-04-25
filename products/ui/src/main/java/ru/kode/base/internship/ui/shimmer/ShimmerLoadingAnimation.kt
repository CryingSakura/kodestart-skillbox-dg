package ru.kode.base.internship.ui.shimmer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

fun Modifier.shimmerLoadingAnimation(
  widthPxOfShadowBrush: Int = 500,
  angleOfAxisY: Float = 270f,
  durationMillis: Int = 1000,
): Modifier {
  return composed {
    val shimmerColors = listOf(
      AppTheme.colors.contendSecondary.copy(alpha = 0.3f),
      AppTheme.colors.contendSecondary.copy(alpha = 0.5f),
      AppTheme.colors.contendSecondary.copy(alpha = 1.0f),
      AppTheme.colors.contendSecondary.copy(alpha = 0.5f),
      AppTheme.colors.contendSecondary.copy(alpha = 0.3f),
    )

    val transition = rememberInfiniteTransition(label = "")

    val translateAnimation = transition.animateFloat(
      initialValue = 0f,
      targetValue = (durationMillis + widthPxOfShadowBrush).toFloat(),
      animationSpec = infiniteRepeatable(
        animation = tween(
          durationMillis = durationMillis,
          easing = LinearEasing,
        ),
        repeatMode = RepeatMode.Restart,
      ),
      label = "Shimmer loading animation",
    )

    this.background(
      brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = translateAnimation.value - widthPxOfShadowBrush, y = 0.0f),
        end = Offset(x = translateAnimation.value, y = angleOfAxisY),
      ),
    )
  }
}
