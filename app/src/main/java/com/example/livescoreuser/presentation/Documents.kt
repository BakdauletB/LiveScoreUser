package com.example.livescoresdu.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.livescoresdu.R
import com.example.livescoresdu.uilibrary.values.CustomButton
import com.example.livescoresdu.uilibrary.values.click
import ffinbank.myfreedom.uilibrary.values.*
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode


@Composable
fun Documents(
    documents: List<File>,
    maxSize: Int,
    onOpenBottomSheetClick: () -> Unit,
    onAddDocumentClick: () -> Unit,
    onDeleteDocumentClick: (File) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = spacing16, end = spacing16)
            .background(
                color = Orange500,
                shape = RoundedCornerShape(cornerRadius16)
            )

            .clip(shape = RoundedCornerShape(cornerRadius12))
            .padding(start = spacing16, end = spacing16, bottom = spacing16)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = spacing16, bottom = spacing16)
        ) {
            Text(
                text = "Upload excel file",
                fontSize = fontSize18,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
                color = Base900,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_info_default),
                contentDescription = null, modifier = Modifier.click {
                    onOpenBottomSheetClick()
                }
            )
        }
        CustomButton(
            buttonColors = ButtonDefaults.buttonColors(backgroundColor = Base200),
            modifier = Modifier
                .fillMaxWidth(),
            content = {
                Spacer(modifier = Modifier.width(spacing8))
                Text(
                    text = "File",
                    fontSize = fontSize16,
                    fontWeight = FontWeight.SemiBold,
                    style = semiBold,
                    color = Base800
                )
            },
            enabled = documents.size < maxSize
        ) {
            onAddDocumentClick()
        }
        Spacer(modifier = Modifier.height(spacing12))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Размер файла не должен превышать - 10 МБ",
            fontSize = fontSize13,
            fontWeight = FontWeight.Normal,
            style = regular,
            color = Base700,
            textAlign = TextAlign.Center
        )
        repeat(documents.size) {
            Spacer(modifier = Modifier.height(spacing8))
            DocumentItem(
                document = documents[it],
                onDeleteClick = {
                    onDeleteDocumentClick(documents[it])
                }
            )
        }

    }

}

@Composable
private fun DocumentItem(document: File, onDeleteClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Base100, shape = RoundedCornerShape(cornerRadius12))
            .padding(horizontal = spacing16, vertical = spacing8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_document),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(spacing8))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = document.name,
                fontSize = fontSize14,
                fontWeight = FontWeight.Medium,
                style = medium,
                color = Base900,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${
                    BigDecimal(document.readBytes().size / 1000000.0).setScale(
                        1,
                        RoundingMode.UP
                    )
                } МБ",
                fontSize = fontSize13,
                fontWeight = FontWeight.Normal,
                style = regular,
                color = Base700
            )
        }
        Spacer(modifier = Modifier.width(spacing8))
        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null,
            modifier = Modifier.click {
                onDeleteClick()
            }
        )
    }
}












