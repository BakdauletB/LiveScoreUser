package com.example.livescore.presentation.screens.scorers.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.livescore.util.*
import com.example.livescoresdu.data.response.PointsResponse
import ffinbank.myfreedom.uilibrary.values.*

@Composable
fun PointsListItem(pointsResponse: PointsResponse, count: Int){
//    LazyColumn() {
//        item {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(spacing16)
        ) {
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
            pointsResponse.teamName?.let { Text(text = it,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
                color =  Base50) }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = pointsResponse.gamePlayed.toString(),
                color = Base50,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
            )
            Spacer(modifier = Modifier.width(spacing32))
            Text(
                text = "${pointsResponse.winCount-pointsResponse.loseCount}",
                color = Base50,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
            )
            Spacer(modifier = Modifier.width(spacing36))
            Text(
                text = pointsResponse.points.toString(),
                color = Base50,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            color = Base700
        )

}
fun getTeamKzLogo(team: PointsResponse): Int {
    return when (team.points) {
        1 -> com.example.livescoreuser.R.drawable.barabar
        2 -> com.example.livescoreuser.R.drawable.sunkar

        else -> 0
    }
}