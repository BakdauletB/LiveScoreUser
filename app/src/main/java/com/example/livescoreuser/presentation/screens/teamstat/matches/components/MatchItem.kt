package com.example.livescore.presentation.screens.matches.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberImagePainter
import com.example.livescore.data.remote.dto.GameDateResponse
import com.example.livescore.data.remote.dto.matches.News
import com.example.livescore.util.*

@Composable
fun MatchItem(
    modifier: Modifier,
    gameDateResponse: GameDateResponse,
    onClick:(match:GameDateResponse)-> Unit
){
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(spacing16)
            .clickable { onClick(gameDateResponse) }
            .background(shape = RoundedCornerShape(cornerRadius12), color = Base800)
            .clip(RoundedCornerShape(cornerRadius16))
            .padding(spacing16)
    ) {
        Text(text = "${
            gameDateResponse.gameDateTime?.let { convertDate(it, fromFormat = "yyyy-MM-dd'T'HH:mm:ss", toFormat = "HH:mm")}
        }",color = Base500,
            fontSize = fontSize13,
            style = medium,
            fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.width(spacing16))
        Column(modifier = modifier.weight(1f)) {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Image(painter = rememberImagePainter(gameDateResponse.team1Logo), contentDescription = null, modifier = modifier.size(
                    spacing32))
                Spacer(modifier = Modifier.width(spacing6))
                Text(text = gameDateResponse.team1Name,
                    color = Base500,
                    fontSize = fontSize13,
                    style = medium,
                    fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(spacing6))
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Image(painter = rememberImagePainter(gameDateResponse.team2Logo), contentDescription = null,modifier= modifier.size(
                    spacing32))
                Spacer(modifier = Modifier.width(spacing6))
                Text(text = gameDateResponse.team2Name,
                    color = Base500,
                    fontSize = fontSize13,
                    style = medium,
                    fontWeight = FontWeight.Medium)
            }
        }
        Column() {
            Text(text = "${gameDateResponse.gameScore?.get(0)}",
            color = Base500,
            fontSize = fontSize13,
            style = medium,
            fontWeight = FontWeight.Medium)
            Spacer(modifier = modifier.height(spacing10))
            Text(text = "${gameDateResponse.gameScore?.get(2)}",
                color = Base500,
                fontSize = fontSize13,
                style = medium,
                fontWeight = FontWeight.Medium)
        }

    }

}