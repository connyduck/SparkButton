package at.connyduck.sparkbutton.compose

import androidx.compose.animation.core.Easing

/** an easing similar to OvershootInterpolator */
internal class OvershootEasing(
    val tension: Float = 2.0f
) : Easing {
    override fun transform(fraction: Float): Float {
        val t = fraction - 1.0f
        return t * t * ((tension + 1) * t + tension) + 1.0f
    }
}

/** an easing similar to DecelerateInterpolator */
internal object DecelerateEasing : Easing {
    override fun transform(fraction: Float): Float = (1.0f - (1.0f - fraction) * (1.0f - fraction))
}
