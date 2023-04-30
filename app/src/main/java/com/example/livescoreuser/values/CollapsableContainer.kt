package com.example.livescoresdu.uilibrary.values

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.livescoreuser.R

import ffinbank.myfreedom.uilibrary.values.*

@Composable
fun CollapsableContainer(
    title: String,
    expanded: Boolean,
    onExpandClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                color = Base50,
                shape = RoundedCornerShape(cornerRadius12)
            )
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = if (expanded) null else rememberRipple(color = ClickRippleColor)
            ) {
                onExpandClick()
            }
            .padding(
                top = spacing16,
                bottom = spacing12
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = spacing16,
                    end = spacing16,
                    bottom = spacing4
                )
        ) {
            Text(
                text = title,
                fontSize = fontSize13,
                fontWeight = FontWeight.Medium,
                style = medium,
                color = Base700,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_drop_down),
                contentDescription = null,
                tint = Base700,
                modifier = Modifier
                    .size(spacing16)
                    .rotate(
                        if (expanded) rotate(to = -90f) else rotate(to = 90f)
                    )
            )
        }

        AnimatedVisibility(expanded) {
            content()
        }
    }
}
