package com.example.livescore.presentation.screens.teamstat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.livescore.presentation.screens.scorers.components.GoalListItem
import com.example.livescore.presentation.screens.standings.TeamYellowCardsState
import com.example.livescore.util.*

@Composable
fun TeamYellowCards(teamYellowCardsState: TeamYellowCardsState){
    LazyColumn {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Yellow Cards",
                    fontSize = fontSize16,
                    fontWeight = FontWeight.SemiBold,
                    style = semiBold,
                    color =  Base700
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Per\ngame",
                    fontSize = fontSize13,
                    fontWeight = FontWeight.Medium,
                    style = medium,
                    color =  Base700
                )
                Spacer(modifier = Modifier.width(spacing6))
                Text(text = "Total",
                    fontSize = fontSize13,
                    fontWeight = FontWeight.Medium,
                    style = medium,
                    color =  Base700
                )
            }
        }
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(shape = RoundedCornerShape(spacing12), color = Base900)
                .border(
                    width = 1.5.dp,
                    color = Base700,
                    shape = RoundedCornerShape(spacing16)
                )
                .clip(RoundedCornerShape(spacing12))
            ){
                teamYellowCardsState.standings.forEachIndexed {index,team ->
                    GoalListItem(team = team, count = index+1)
                }
            }
        }
    }

}