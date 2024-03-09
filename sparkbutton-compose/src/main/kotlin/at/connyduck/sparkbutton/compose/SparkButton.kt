package at.connyduck.sparkbutton.compose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A Button that shows an icon and plays a sparkly animation when clicked.
 * @param checked - whether this SparkButton is currently checked
 * @param onCheckedChange - callback to be invoked when this SparkButton is selected
 * @param enabled enabled whether or not this SparkButton will handle input events and appear
 * enabled for semantics purposes
 * @param modifier optional Modifier for this button
 * @param interactionSource the [MutableInteractionSource] representing the stream of
 * [Interaction]s for this Button. You can create and pass in your own remembered
 * [MutableInteractionSource] if you want to observe [Interaction]s and customize the
 * appearance / behavior of this Button in different [Interaction]s.
 * @param primaryColor the primary color of the sparkles, defaults to #FFC107
 * @param secondaryColor the secondary color of the sparkles, defaults to #FF5722
 * @param animationSpeed Set to a number between 0 and 1 to slow the animation down or to over 1 to speed it up. Defaults to 1 which equals a 1 second animation.
 * @param content the content (icon) to be drawn inside the SparkButton.
 */
@Composable
public fun SparkButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    primaryColor: Color = Color(0xFFFFC107),
    secondaryColor: Color = Color(0xFFFF5722),
    animationSpeed: Float = 1.0f,
    content: @Composable () -> Unit
) {
    println("button: $checked")

    require(animationSpeed > 0f) {
        "animationSpeed must be larger than 0"
    }

    val primaryColorDark = remember(primaryColor) { primaryColor.darken(0.1f) }
    val secondaryColorDark = remember(secondaryColor) { secondaryColor.darken(0.1f) }

    var buttonClicks: Int by remember { mutableIntStateOf(0) }

    val contentScale = remember {
        Animatable(1f)
    }

    val dotsRadiusProgress = remember {
        Animatable(0.0f)
    }

    val dotsSizeProgress = remember {
        Animatable(0.0f)
    }

    val dotsSizeProgress2 = remember {
        Animatable(0.0f)
    }

    LaunchedEffect(buttonClicks) {
        if (checked && buttonClicks > 0) {
            launch {
                contentScale.snapTo(0.0f)
                delay(timeMillis = (250 / animationSpeed).toLong())
                contentScale.snapTo(0.2f)
                contentScale.animateTo(
                    targetValue = 1f,
                    animationSpec =
                    tween(
                        durationMillis = (350 / animationSpeed).toInt(),
                        easing = OvershootEasing(4.0f)
                    )
                )
            }

            launch {
                dotsRadiusProgress.animateTo(
                    targetValue = 1.0f,
                    animationSpec =
                    keyframes {
                        0f at 0 using FastOutSlowInEasing
                        0.9f at (750 / animationSpeed).toInt() using SlowOutFastInEasing
                        1.0f at (1000 / animationSpeed).toInt()
                        durationMillis = (1000 / animationSpeed).toInt()
                    }
                )
                dotsRadiusProgress.snapTo(0.0f)
            }

            launch {
                dotsSizeProgress.animateTo(
                    targetValue = 0f,
                    animationSpec =
                    keyframes {
                        0f at 0 using FastOutSlowInEasing
                        1f at (450 / animationSpeed).toInt()
                        1f at (650 / animationSpeed).toInt() using SlowOutFastInEasing
                        0.0f at (1000 / animationSpeed).toInt()
                        durationMillis = (1000 / animationSpeed).toInt()
                    }
                )
            }

            launch {
                dotsSizeProgress2.animateTo(
                    targetValue = 0f,
                    animationSpec =
                    keyframes {
                        0f at 0 using FastOutSlowInEasing
                        1f at (500 / animationSpeed).toInt() using SlowOutFastInEasing
                        0.0f at (1000 / animationSpeed).toInt()
                        durationMillis = (1000 / animationSpeed).toInt()
                    }
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        interactionSource.interactions.collect {
            when (it) {
                is PressInteraction.Press ->
                    launch {
                        contentScale.animateTo(
                            targetValue = 0.8f,
                            animationSpec =
                            tween(
                                durationMillis = 150,
                                easing = LinearEasing
                            )
                        )
                    }
                is PressInteraction.Cancel -> contentScale.snapTo(1.0f)
                is PressInteraction.Release -> contentScale.snapTo(1.0f)
            }
        }
    }

    Box(
        modifier =
        modifier
            .toggleable(
                value = checked,
                interactionSource = interactionSource,
                enabled = enabled,
                indication = null,
                role = Role.Checkbox,
                onValueChange = { newValue ->
                    onCheckedChange(newValue)
                    buttonClicks++
                }
            )
            .scale(contentScale.value)
            .alpha(
                if (enabled) {
                    1f
                } else {
                    disabledAlpha
                }
            )
            .drawBehind {
                val maxDotSize = this.size.maxDimension / 24

                val maxOuterDotsRadius: Float = this.size.maxDimension * 1.5f

                val currentRadius1: Float =
                    mapValueFromRangeToRange(
                        dotsRadiusProgress.value,
                        0.0f,
                        1f,
                        0f,
                        maxOuterDotsRadius
                    )

                val currentRadius2: Float =
                    mapValueFromRangeToRange(
                        dotsRadiusProgress.value,
                        0.0f,
                        1f,
                        0f,
                        maxOuterDotsRadius / 8 * 7
                    )

                val currentRadius3: Float =
                    mapValueFromRangeToRange(
                        dotsRadiusProgress.value,
                        0.0f,
                        1f,
                        0f,
                        maxOuterDotsRadius / 4 * 3
                    )

                val dotColors =
                    if (dotsRadiusProgress.value < 0.5f) {
                        val progress =
                            mapValueFromRangeToRange(
                                dotsRadiusProgress.value,
                                0.0f,
                                0.5f,
                                0.0f,
                                1.0f
                            )
                        listOf(
                            primaryColor.interpolate(primaryColorDark, progress),
                            primaryColorDark.interpolate(secondaryColor, progress),
                            secondaryColor.interpolate(secondaryColorDark, progress),
                            secondaryColorDark.interpolate(primaryColor, progress)
                        )
                    } else {
                        val progress =
                            mapValueFromRangeToRange(
                                dotsRadiusProgress.value,
                                0.5f,
                                1.0f,
                                0.0f,
                                1.0f
                            )
                        listOf(
                            primaryColorDark.interpolate(primaryColor, progress),
                            secondaryColor.interpolate(primaryColorDark, progress),
                            secondaryColorDark.interpolate(secondaryColor, progress),
                            primaryColor.interpolate(secondaryColorDark, progress)
                        )
                    }

                // outer Dots
                for (i in 0 until DOT_COUNT) {
                    val cX: Float =
                        (center.x + currentRadius1 * cos(i * DOT_POSITION_ANGLE * PI / 180f)).toFloat()
                    val cY: Float =
                        (center.y + currentRadius1 * sin(i * DOT_POSITION_ANGLE * PI / 180f)).toFloat()
                    drawCircle(
                        color = dotColors[i % dotColors.size],
                        radius = dotsSizeProgress2.value * maxDotSize,
                        center = Offset(cX, cY)
                    )
                }

                // innerDots
                for (i in 0 until DOT_COUNT) {
                    val cX: Float =
                        (center.x + currentRadius2 * cos((i * DOT_POSITION_ANGLE - DOT_POSITION_ANGLE / 2f) * PI / 180f)).toFloat()
                    val cY: Float =
                        (center.y + currentRadius2 * sin((i * DOT_POSITION_ANGLE - DOT_POSITION_ANGLE / 2f) * PI / 180f)).toFloat()
                    drawCircle(
                        color = dotColors[(i + 1) % dotColors.size],
                        radius = dotsSizeProgress.value * 2 * maxDotSize,
                        center = Offset(cX, cY)
                    )
                }

                // innerDots
                for (i in 0 until DOT_COUNT) {
                    val cX: Float =
                        (center.x + currentRadius3 * cos((i * DOT_POSITION_ANGLE) * PI / 180f)).toFloat()
                    val cY: Float =
                        (center.y + currentRadius3 * sin((i * DOT_POSITION_ANGLE) * PI / 180f)).toFloat()
                    drawCircle(
                        color = dotColors[(i + 1) % dotColors.size],
                        radius = dotsSizeProgress2.value * maxDotSize,
                        center = Offset(cX, cY)
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

private const val DOT_COUNT = 12
private const val DOT_POSITION_ANGLE: Float = 360f / DOT_COUNT

private val SlowOutFastInEasing = CubicBezierEasing(0.8f, 0.0f, 0.4f, 1.0f)

private val disabledAlpha = 0.38f
