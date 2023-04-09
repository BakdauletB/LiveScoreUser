package com.example.livescoresdu.uilibrary.values

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import ffinbank.myfreedom.uilibrary.values.*

private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f
private const val SMALL_TEXT_SCALE_REDUCTION_INTERVAL = 0.6f

@Composable
fun ResponsiveText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = StaticBase50,
    style: TextStyle = bold,
    lineHeight: TextUnit = lineHeight32,
    fontWeight: FontWeight = FontWeight.Bold,
    targetTextSizeHeight: TextUnit = fontSize40,
    maxLines: Int = 1,
) {
    var textSize by remember { mutableStateOf(targetTextSizeHeight) }

    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = textSize,
        textAlign = TextAlign.Center,
        style = style,
        lineHeight = lineHeight,
        fontWeight = fontWeight,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = { textLayoutResult ->
            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1

            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                textSize = textSize.times(
                    if (targetTextSizeHeight == fontSize13) {
                        SMALL_TEXT_SCALE_REDUCTION_INTERVAL
                    } else {
                        TEXT_SCALE_REDUCTION_INTERVAL
                    }
                )
            }
        }
    )
}
