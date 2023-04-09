package com.example.livescoresdu.uilibrary.values

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ffinbank.myfreedom.uilibrary.values.*
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

fun Modifier.advancedShadow(
    color: Color = Base900.copy(alpha = 0.05f),
    alpha: Float = 0.05f,
    cornersRadius: Dp = cornerRadius12,
    shadowBlurRadius: Dp = 20.dp,
    offsetY: Dp = 5.dp,
    offsetX: Dp = 0.dp
) = drawBehind {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()

    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
//        it.drawRect(Rect(left = 0f, top = 0f, right = this.size.width, this.size.height), paint = s)
        it.drawRoundRect(
            1f,
            1f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}

//Without click indication
fun Modifier.click(enabled: Boolean = true, onClick: () -> Unit) = clickable(
    interactionSource = androidx.compose.foundation.interaction.MutableInteractionSource(),
    indication = null,
    onClick = onClick,
    enabled = enabled
)

//Invisible modifier
fun Modifier.isVisible(visible: Boolean) = alpha(if (visible) 1f else 0f)

fun Modifier.advancedBlur(
    color: Color = Base50.copy(alpha = 0.05f),
    alpha: Float = 0.05f,
    cornersRadius: Dp = cornerRadius12,
    shadowBlurRadius: Dp = 20.dp,
    offsetY: Dp = 5.dp,
    offsetX: Dp = 0.dp
) = drawBehind {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()

    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
//        it.drawRect(Rect(left = 0f, top = 0f, right = this.size.width, this.size.height), paint = s)
        it.drawRoundRect(
            1f,
            1f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}


//@OptIn(ExperimentalComposeUiApi::class)
//fun Modifier.bouncedEffect(clicked: () -> Unit): Modifier = composed {
//    var clickedValue by remember {
//        mutableStateOf(1f)
//    }
//    val scaling = scaling(targetValue = clickedValue)
//
//    return@composed Modifier
//        .graphicsLayer {
//            scaleX = scaling
//            scaleY = scaling
//        }
//        .pointerInteropFilter {
//            when (it.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    clickedValue = 0.9f
//                }
//                MotionEvent.ACTION_UP -> {
//                    clicked()
//                    clickedValue = 1f
//                }
//                MotionEvent.ACTION_CANCEL -> {
//                    clickedValue = 1f
//                }
//            }
//            true
//        }
//}

fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
    Modifier.drawBehind {

        // Setting the angle in radians
        val angleRad = angle / 180f * PI

        // Fractional x
        val x = kotlin.math.cos(angleRad).toFloat()

        // Fractional y
        val y = kotlin.math.sin(angleRad).toFloat()

        // Set the Radius and offset as shown below
        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        // Setting the exact offset
        val exactOffset = Offset(
            x = kotlin.math.min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - kotlin.math.min(offset.y.coerceAtLeast(0f), size.height)
        )

        // Draw a rectangle with the above values
        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )

    }
)

fun Modifier.textBrush(brush: Brush) = this
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush, blendMode = BlendMode.SrcAtop)
        }
    }

//fun Modifier.collapsableBottomSheet(screenHeight: Dp) = this
//    .fillMaxWidth()
//    .heightIn(max = screenHeight - defaultToolbarHeight - 11.dp)
//    .background(
//        color = Base100,
//        shape = RoundedCornerShape(
//            topStart = cornerRadius16,
//            topEnd = cornerRadius16
//        )
//    )

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier =
    composed {
        clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }
    }

val horizontalScrollConsumer = object : NestedScrollConnection {
    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource) = available.copy(y = 0f)
}

val verticalScrollConsumer = object : NestedScrollConnection {
    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource) = available.copy(x = 0f)
}

fun Modifier.disableHorizontalNestedScroll() = this.nestedScroll(horizontalScrollConsumer)

fun Modifier.disableVerticalNestedScroll() = this.nestedScroll(verticalScrollConsumer)
