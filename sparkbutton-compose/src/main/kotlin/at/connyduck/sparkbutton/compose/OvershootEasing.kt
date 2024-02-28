package at.connyduck.sparkbutton.compose

import androidx.compose.animation.core.Easing

/** an easing similar to overshoot interpolator */
internal class OvershootEasing(
    val tension: Float = 2.0f
) : Easing {
    override fun transform(fraction: Float): Float {
        val t = fraction - 1.0f
        return t * t * ((tension + 1) * t + tension) + 1.0f
    }
}
