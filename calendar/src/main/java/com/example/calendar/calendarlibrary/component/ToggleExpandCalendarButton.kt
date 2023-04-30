package com.mabn.calendarlibrary.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.calendar.R

@Composable
fun ToggleExpandCalendarButton(
    isExpanded: Boolean,
    expand: () -> Unit,
    collapse: () -> Unit,
    color: Color
) {
    IconToggleButton(
        checked = isExpanded,
        onCheckedChange = { isChecked -> if (isChecked) expand() else collapse() }
    ) {
        if (isExpanded) {
            Icon(painter = painterResource(id = R.drawable.calendar), "Collapse calendar", tint = color,
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp))
        } else {
            Icon(painter = painterResource(id = R.drawable.calendar), "Expand calendar", tint = color,
                modifier = Modifier
                    .width(32.dp)
                .height(32.dp))
        }

    }
}