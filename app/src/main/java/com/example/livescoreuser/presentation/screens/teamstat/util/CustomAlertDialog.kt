package com.example.livescore.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livescore.presentation.click

@Composable
fun CustomAlertDialog(
    title: String,
    description: String = "",
    hint: String = "",
    positiveButtonColor: Color = Green500,
    positiveButtonText: String = "",
    negativeButtonColor: Color = Base700,
    negativeButtonText: String = "",
    titleMaxLines: Int = 2,
    descriptionMaxLines: Int = 4,
    positiveButtonClick: () -> Unit,
    negativeButtonClick: () -> Unit = {},
    onDismissRequest: () -> Unit
) = AlertDialog(
    onDismissRequest = onDismissRequest, title = {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Create game",
            fontSize = fontSize18,
            fontWeight = FontWeight.SemiBold,
            style = semiBold,
            color = Orange500,
            maxLines = titleMaxLines,
            overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(spacing16))
            Text(text = "Tournament",
                fontSize = fontSize13,
                color =  Base50,
                fontWeight = FontWeight.Medium,
                style = medium)
            Spacer(modifier = Modifier.height(spacing6))
            DropDownText(text = "", modifier = Modifier, onClick = {

            })
            Spacer(modifier = Modifier.height(spacing6))
            Text(text = "Group",
                fontSize = fontSize13,
                color =  Base50,
                fontWeight = FontWeight.Medium,
                style = medium)
            Spacer(modifier = Modifier.height(spacing6))
            DropDownText(text = "", modifier = Modifier, onClick = {
            })
            Spacer(modifier = Modifier.height(spacing6))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Home",
                        fontSize = fontSize13,
                        color =  Base50,
                        fontWeight = FontWeight.Medium,
                        style = medium)
                    Spacer(modifier = Modifier.height(spacing6))
                    DropDownText(text = "", modifier = Modifier, onClick = {
                    })
                }
                Spacer(modifier = Modifier.width(spacing6))
                Column(modifier = Modifier.weight(1f),horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Away",
                        fontSize = fontSize13,
                        color =  Base50,
                        fontWeight = FontWeight.Medium,
                        style = medium)
                    Spacer(modifier = Modifier.height(spacing6))
                    DropDownText(text = "", modifier = Modifier, onClick = {
                    })
                }
            }
            Text(text = "Date time",
                fontSize = fontSize13,
                color =  Base50,
                fontWeight = FontWeight.Medium,
                style = medium)
            Spacer(modifier = Modifier.height(spacing6))
            DropDownText(text = "", modifier = Modifier, onClick = {
            })
        }
    },
    buttons = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .click { negativeButtonClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = negativeButtonText,
                    fontWeight = FontWeight.SemiBold,
                    style = semiBold,
                    fontSize = fontSize18,
                    color = negativeButtonColor,
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .click { positiveButtonClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = positiveButtonText,
                    fontWeight = FontWeight.SemiBold,
                    style = semiBold,
                    fontSize = fontSize18,
                    color = positiveButtonColor,
                    textAlign = TextAlign.Center
                )
            }
        }
    },
    shape = RoundedCornerShape(cornerRadius12),
    backgroundColor = Base700,
    modifier = Modifier.wrapContentWidth()
)
