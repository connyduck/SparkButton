package at.connyduck.sparkbutton.compose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces

internal fun mapValueFromRangeToRange(
    value: Float,
    fromLow: Float,
    fromHigh: Float,
    toLow: Float,
    toHigh: Float
): Float = toLow + (value - fromLow) / (fromHigh - fromLow) * (toHigh - toLow)

internal fun Color.darken(amount: Float): Color {
    if (amount < 0f || amount > 1f) {
        throw IllegalArgumentException("amount must be between 0 and 1")
    }
    val rgbColor = this.convert(ColorSpaces.Srgb)
    val hsv = floatArrayOf(0f, 0f, 0f)
    android.graphics.Color.RGBToHSV(
        (rgbColor.red * 255).toInt(),
        (rgbColor.green * 255).toInt(),
        (rgbColor.blue * 255).toInt(),
        hsv
    )

    return Color.hsv(hsv[0], hsv[1], hsv[2] * (1f - amount))
}

internal fun Color.interpolate(other: Color, fraction: Float): Color {
    if (fraction < 0f || fraction > 1f) {
        throw IllegalArgumentException("fraction must be between 0 and 1")
    }
    val (startR, startG, startB, startA) = this.convert(ColorSpaces.Srgb)
    val (endR, endG, endB, endA) = other.convert(ColorSpaces.Srgb)

    val r = startR + fraction * (endR - startR)
    val g = startG + fraction * (endG - startG)
    val b = startB + fraction * (endB - startB)
    val a = startA + fraction * (endA - startA)

    return Color(r, g, b, a)
}
