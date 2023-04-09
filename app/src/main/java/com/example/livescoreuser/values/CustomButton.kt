package com.example.livescoresdu.uilibrary.values

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import ffinbank.myfreedom.uilibrary.values.*

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = Green500,
        contentColor = Base50,
        disabledBackgroundColor = Green300,
        disabledContentColor = Base50
    ),
    enabled: Boolean = true,
    minClickSec: Long = 500,
    height: Dp = 52.dp,
    maxHeight: Dp = 52.dp,
    cornerRadius: Dp = cornerRadius16,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    content: @Composable RowScope.() -> Unit,
    onButtonClicked: () -> Unit
) {
    var lastClickSec = remember {
        0L
    }
    Button(
        onClick = {
            if (System.currentTimeMillis() - lastClickSec > minClickSec) {
                lastClickSec = System.currentTimeMillis()
                onButtonClicked()
            }
        },
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .heightIn(min = height, max = maxHeight),
        colors = buttonColors,
        enabled = enabled,
        content = content,
        elevation = elevation
    )
}

@Composable
fun CustomButtonText(
    text: String,
    color: Color = StaticBase50,
    fontSize: TextUnit = fontSize18,
    icon: (@Composable () -> Unit)? = null,
    showIcon: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResponsiveText(
            modifier = Modifier.weight(1f, fill = false),
            text = text,
            targetTextSizeHeight = fontSize,
            fontWeight = FontWeight.SemiBold,
            lineHeight = lineHeight24,
            style = semiBold,
            color = color
        )
        if (icon != null && showIcon) {
            Spacer(modifier = Modifier.width(spacing8))
            icon()
        }
    }
}

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    buttonColors: Color = Green500,
    height: Dp = 40.dp,
    cornerRadius: Dp = cornerRadius8,
    contentText: String,
    textStyle: TextStyle = semiBold,
    onButtonClicked: () -> Unit
) =
    Box(
        modifier = modifier
            .height(height)
            .widthIn(min = 132.dp)
            .clip(
                RoundedCornerShape(cornerRadius)
            )
            .background(color = buttonColors)
            .clickable {
                onButtonClicked()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = contentText,
            style = textStyle,
            color = Base50
        )
    }
