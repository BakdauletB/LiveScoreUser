package com.example.livescoresdu.uilibrary.values

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.livescoreuser.R
import ffinbank.myfreedom.uilibrary.values.Base200
import ffinbank.myfreedom.uilibrary.values.Base500
import ffinbank.myfreedom.uilibrary.values.Base900
import ffinbank.myfreedom.uilibrary.values.fontSize16
import ffinbank.myfreedom.uilibrary.values.lineHeight22
import ffinbank.myfreedom.uilibrary.values.regular

internal val defaultTextFieldTextStyle =
    regular.copy(color = Base900, fontSize = fontSize16, lineHeight = lineHeight22)

internal val defaultTextFieldHintTextStyle =
    regular.copy(color = Base500, fontSize = fontSize16, lineHeight = lineHeight22)


@Composable
internal fun DefaultTextFieldColors(cursorColor: Color): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        textColor = Base900,
        backgroundColor = Base200,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        cursorColor = cursorColor,
        disabledTextColor = Base900,
        disabledIndicatorColor = Color.Transparent
    )
}


@Composable
internal fun DefaultTrailingIcon(
    showIcon: Boolean,
    onTrailingIconClick: () -> Unit
) {
    if (showIcon) {
        Image(
            painter = painterResource(id = R.drawable.ic_close_base400),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(24.dp)
                .click(onClick = onTrailingIconClick)
        )
    }

}
