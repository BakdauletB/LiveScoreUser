package com.example.livescoresdu.uilibrary.values

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ffinbank.myfreedom.uilibrary.values.*

@Composable
fun DropDownText(
    text: String,
    modifier: Modifier,
    hint: String = "",
    paddingStart: Dp = spacing8,
    paddingTop: Dp = spacing8,
    paddingEnd: Dp = spacing8,
    paddingBottom: Dp = spacing8,
    maxWidth: Boolean = true,
    dropdownTint: Color = Orange500,
    fontSize: Float = fontSize16.value,
    dropdownHeight: Dp = spacing24,
    dropdownWidth: Dp = spacing24,
    backgroundColor: Color = textcolor,
    image: Any? = null,
    onClick: () -> Unit
){
    Row(
        modifier = modifier
            .click {
                onClick()
            }
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(cornerRadius12)
            )
            .padding(
                start = paddingStart,
                top = paddingTop,
                end = paddingEnd,
                bottom = paddingBottom
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val textModifier = if (maxWidth) {
            Modifier.weight(1f)
        } else {
            Modifier
        }
        if (image != null) {
            AsyncImage(model = image, contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(spacing8))
        }
        Text(
            text = text.ifEmpty { hint },
            fontSize = fontSize.sp,
            color = if (text.isEmpty()) Base500 else Base50,
            fontWeight = FontWeight.Normal,
            style = regular,
            modifier = textModifier
        )


        Spacer(modifier = Modifier.width(spacing8))

        Icon(
            painter = painterResource(id = com.example.livescoresdu.R.drawable.ic_drop_down),
            contentDescription = null,
            modifier = Modifier
                .height(dropdownHeight)
                .width(dropdownWidth),
            tint = dropdownTint
        )
    }

}