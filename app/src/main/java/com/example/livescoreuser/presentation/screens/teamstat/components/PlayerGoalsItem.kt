package com.example.livescore.presentation.screens.scorers.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.livescore.util.*
import com.example.livescoresdu.data.response.PlayerGoalsResponse
import ffinbank.myfreedom.uilibrary.values.*

@Composable
fun PlayerGoalsItem(playerGoalsResponse: PlayerGoalsResponse, count:Int){
    Column() {
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
                painter = painterResource(id = com.example.livescoreuser.R.drawable.sunkar),
                contentDescription = "Team logo",
                modifier = Modifier
                    .size(spacing32)
            )
            Spacer(modifier = Modifier.width(spacing6))
            playerGoalsResponse.playerName?.let { Text(text = it,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
                color =  Base50
            ) }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = playerGoalsResponse.total .toString(),
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
}