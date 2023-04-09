package com.example.livescore.presentation.screens.playerstat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.livescore.presentation.screens.scorers.components.PlayerGoalsItem
import com.example.livescore.presentation.screens.standings.YellowCardState
import com.example.livescore.util.*

@Composable
fun YellowCards(yellowCardState: YellowCardState){
    Column() {
        Text(text = "Yellow Card",
            fontSize = fontSize16,
            fontWeight = FontWeight.SemiBold,
            style = semiBold,
            color =  Base700,
            modifier = Modifier.padding(spacing16))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = spacing16, end = spacing16,
                bottom = spacing16
            )
            .background(shape = RoundedCornerShape(spacing12), color = Base900)
            .border(
                width = 1.5.dp,
                color = Base700,
                shape = RoundedCornerShape(spacing16)
            )
            .clip(RoundedCornerShape(spacing12))
        ){
            yellowCardState.points.forEachIndexed { index,yellow ->
                PlayerGoalsItem(playerGoalsResponse = yellow,index+1)
            }
        }
    }
}