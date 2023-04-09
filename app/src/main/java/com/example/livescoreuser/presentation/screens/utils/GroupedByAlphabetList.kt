package com.example.livescoresdu.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.livescoresdu.uilibrary.values.click
import ffinbank.myfreedom.uilibrary.values.*

@Composable
fun GroupedByAlphabetList(list: List<String>, selected: String, filterText: String = "", onClick: (String) -> Unit) {

    val groupedList = list.filter { it.lowercase().contains(filterText.lowercase()) }.sortedBy { it.getOrNull(0) }.groupBy { it.getOrNull(0) }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(groupedList.keys.toList()) {
            AlphabetTextItem(list = groupedList[it].orEmpty(), alphabet = it ?: 'a', selected = selected, onClick = onClick)
            Spacer(modifier = Modifier.height(spacing12))
        }
    }

}

@Composable
private fun AlphabetTextItem(list: List<String>, alphabet: Char, selected: String, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(spacing12))
            .background(color = Base50)
    ) {
        Spacer(modifier = Modifier.height(spacing16))
        AlphabetItem(char = alphabet)
        repeat(list.size) {
            val text = list[it]
            TextItem(
                text = text,
                selected = selected == text,
                onClick = {
                    onClick(text)
                }
            )
        }
    }
}

@Composable
private fun TextItem(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .click {
                onClick()
            }
            .padding(spacing16)
    ) {
        Text(
            text = text,
            fontSize = fontSize16,
            fontWeight = FontWeight.SemiBold,
            style = semiBold,
            color = if (selected) Green500 else Base800,
            modifier = Modifier.weight(1f)
        )
        if (selected) Icon(painter = painterResource(id = com.example.livescoresdu.R.drawable.ic_done_small), contentDescription = null, tint = Green500)
    }
}

@Composable
private fun AlphabetItem(char: Char) {
    Text(
        text = char.toString(),
        fontSize = fontSize13,
        fontWeight = FontWeight.Medium,
        style = medium,
        color = Base500,
        modifier = Modifier.padding(start = spacing16)
    )
}