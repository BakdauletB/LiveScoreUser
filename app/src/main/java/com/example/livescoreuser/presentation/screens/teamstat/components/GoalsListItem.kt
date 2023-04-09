package com.example.livescore.presentation.screens.scorers.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.livescore.util.*
import com.example.livescoresdu.data.response.TeamStatisticsGoalsResponse
import ffinbank.myfreedom.uilibrary.values.*

@Composable
fun GoalListItem(team: TeamStatisticsGoalsResponse, count: Int){
    Column() {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(
            spacing16)) {
            Text(
                text = count.toString(),
                color = Base50,
            )
            Spacer(modifier = Modifier.width(spacing6))
            Image(
                painter = painterResource(id = com.example.livescoreuser.R.drawable.barabar),
                contentDescription = "Team logo",
                modifier = Modifier
                    .size(spacing32)
            )
            Spacer(modifier = Modifier.width(spacing6))
            team.teamName?.let { Text(text = it,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
                color =  Base50) }
            Spacer(modifier = Modifier.weight(1f))
            team.perGame?.let { Text(text = it,
                fontSize = fontSize13,
                fontWeight = FontWeight.Medium,
                style = medium,
                color =  Base700) }
            Spacer(modifier = Modifier.width(spacing16))
            Text(text = team.total.toString(),
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
                color =  Base50)

        }
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            color = Base700
        )
    }
}
fun getTeamKzLogo(team: TeamStatisticsGoalsResponse): Int {
    return when (team.total) {
        1 -> com.example.livescoreuser.R.drawable.barabar
        2 -> com.example.livescoreuser.R.drawable.sunkar

        else -> 0
    }
}