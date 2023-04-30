package com.example.livescore.presentation.screens.playerstat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.livescore.presentation.screens.scorers.components.PlayerGoalsItem
import com.example.livescore.presentation.screens.standings.AssistsGoalState
import com.example.livescore.presentation.screens.standings.PlayersGoalsState
import com.example.livescore.presentation.screens.standings.RedCardState
import com.example.livescore.presentation.screens.standings.YellowCardState
import com.example.livescore.util.*
import com.example.livescoresdu.data.response.PlayerGoalsResponse
import ffinbank.myfreedom.uilibrary.values.*

@Composable
fun All(players: SnapshotStateList<PlayerGoalsResponse>, assistsGoalState: SnapshotStateList<PlayerGoalsResponse>, redCardState: SnapshotStateList<PlayerGoalsResponse>, yellowCardState: SnapshotStateList<PlayerGoalsResponse>){
    LazyColumn(){
        item {
            Text(text = "Goals",
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
                color =  Base700,
                modifier = Modifier.padding(spacing16))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = spacing16, end = spacing16)
                .background(shape = RoundedCornerShape(spacing12), color = Base900)
                .border(
                    width = 1.5.dp,
                    color = Base700,
                    shape = RoundedCornerShape(spacing16)
                )
                .clip(RoundedCornerShape(spacing12))
            ){
                players.forEachIndexed { index,goals ->
                    PlayerGoalsItem(playerGoalsResponse = goals, count = index+1)
                }

//            items(players.points){ standings ->
//                PlayerGoalsItem(playerGoalsResponse = standings)
//            }
            }
        }
        item {
            Spacer(modifier = Modifier.height(spacing16))
            Text(text = "Assists",
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
                color =  Base700,
                modifier = Modifier.padding(spacing16))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = spacing16, end = spacing16)
                .background(shape = RoundedCornerShape(spacing12), color = Base900)
                .border(
                    width = 1.5.dp,
                    color = Base700,
                    shape = RoundedCornerShape(spacing16)
                )
                .clip(RoundedCornerShape(spacing12))
            ){
                assistsGoalState.forEachIndexed {index,assists ->
                    PlayerGoalsItem(playerGoalsResponse = assists,index+1)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(spacing16))
            Text(text = "Red Cards",
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
                color =  Base700,
                modifier = Modifier.padding(spacing16))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = spacing16, end = spacing16)
                .background(shape = RoundedCornerShape(spacing12), color = Base900)
                .border(
                    width = 1.5.dp,
                    color = Base700,
                    shape = RoundedCornerShape(spacing16)
                )
                .clip(RoundedCornerShape(spacing12))
            ){
                redCardState.forEachIndexed {index,redCard ->
                    PlayerGoalsItem(playerGoalsResponse = redCard,index+1)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(spacing16))
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
                yellowCardState.forEachIndexed {index,yellowCard ->
                    PlayerGoalsItem(playerGoalsResponse = yellowCard,index+1)
                }
            }
        }
    }

}