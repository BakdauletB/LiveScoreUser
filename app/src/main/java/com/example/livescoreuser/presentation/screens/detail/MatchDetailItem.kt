package com.example.livescoresdu.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.livescoresdu.data.response.Events
import com.example.livescoresdu.presentation.screens.bundle.EventId
import com.example.livescoreuser.R
import ffinbank.myfreedom.uilibrary.values.*
import org.koin.androidx.compose.getViewModel

@Composable
fun MatchDetailItem(events: Events,
                    alertUpdatePlayer: MutableState<Boolean>,
                    alertUpdateYellow: MutableState<Boolean>,
){
    if (events.eventName == "GOAL" || events.eventName == "YELLOW_CARD" || events.eventName == "RED_CARD") {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.weight(0.3f), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = events.minute.toString(),
                        color = Base50,
                        fontSize = fontSize10,
                        style = regular,
                        fontWeight = FontWeight.Normal)
                    Spacer(modifier = Modifier.width(spacing4))
                    if (events.teamId == 1) {
                        Column {
                            if (events.eventName == "GOAL") {
                                Row(verticalAlignment = Alignment.CenterVertically) {
//                                Text(text = events.minute.toString(), color = Base50)
//                                Spacer(modifier = Modifier.width(spacing6))
                                    Spacer(modifier = Modifier.weight(1f))
                                    Image(
                                        painter = painterResource(id = com.example.livescoreuser.R.drawable.ball),
                                        contentDescription = null,
                                        modifier = Modifier.size(spacing14)
                                    )
                                    Spacer(modifier = Modifier.width(spacing6))
                                    Column() {
                                        Text(
                                            text = events.playerName.substring(0, 9),
                                            color = Base50,
                                            fontSize = fontSize14,
                                            fontWeight = FontWeight.SemiBold,
                                            style = semiBold,
                                        )
                                        if (events.assist?.assistPlayer?.isNotEmpty() == true){
                                            Text(
                                                text = events.assist.assistPlayer.substring(0, 9)
                                                    ?: "",
                                                color = Base50,
                                                fontSize = fontSize11,
                                                fontWeight = FontWeight.Normal,
                                                style = regular,
                                            )
                                        }
                                    }


                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(spacing16))
                        if (events.eventName == "YELLOW_CARD") {
                            Image(
                                painter = painterResource(id = R.drawable.yellowcard),
                                contentDescription = null,
                                modifier = Modifier.size(spacing14)
                            )
                            Spacer(modifier = Modifier.width(spacing6))
                            Text(text = events.playerName.substring(0, 9),
                                color = Base50,
                                fontSize = fontSize14,
                                fontWeight = FontWeight.SemiBold,
                                style = semiBold)
                        }
                        Spacer(modifier = Modifier.height(spacing16))
                        if (events.eventName == "RED_CARD") {
                            Image(
                                painter = painterResource(id = R.drawable.red_card),
                                contentDescription = null,
                                modifier = Modifier.size(spacing14)
                            )
                            Spacer(modifier = Modifier.width(spacing6))
                            Text(text = events.playerName.substring(0, 9),
                                color = Base50,
                                fontSize = fontSize14,
                                fontWeight = FontWeight.SemiBold,
                                style = semiBold)
                        }
                    }
                    Spacer(modifier = Modifier.width(spacing16))
                }
                Spacer(modifier = Modifier.width(spacing16))
                Text(text = events.gameScore, color = Base50,)
                Row(modifier = Modifier.weight(0.3f), verticalAlignment = Alignment.CenterVertically) {
                    if (events.teamId == 2) {
                        Spacer(modifier = Modifier.width(spacing16))
                        Column() {
                            if (events.eventName == "GOAL") {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ball),
                                        contentDescription = null,
                                        modifier = Modifier.size(spacing14)
                                    )
                                    Spacer(modifier = Modifier.width(spacing6))
                                    Text(text = events.playerName.substring(0, 9),
                                        color = Base50,
                                        fontSize = fontSize14,
                                        fontWeight = FontWeight.SemiBold,
                                        style = semiBold
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(spacing16))
                        if (events.eventName == "RED_CARD") {
                            Image(
                                painter = painterResource(id = R.drawable.red_card),
                                contentDescription = null,
                                modifier = Modifier.size(spacing14)
                            )
                            Spacer(modifier = Modifier.width(spacing6))
                            Text(text = events.playerName.substring(0, 9),
                                color = Base50,
                                fontSize = fontSize14,
                                fontWeight = FontWeight.SemiBold,
                                style = semiBold)
                        }
                        Spacer(modifier = Modifier.height(spacing16))
                        if (events.eventName == "YELLOW_CARD") {
                            Image(
                                painter = painterResource(id = R.drawable.yellowcard),
                                contentDescription = null,
                                modifier = Modifier.size(spacing14)
                            )
                            Spacer(modifier = Modifier.width(spacing6))
                            Text(text = events.playerName.substring(0, 9),
                                color = Base50,
                                fontSize = fontSize14,
                                fontWeight = FontWeight.SemiBold,
                                style = semiBold)
                        }
                    }
                }
                Image(painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = null,
                    modifier = Modifier
                        .size(spacing14)
                        .clickable {
//                            viewModel.getEventId(events.eventId)
                            EventId.eventId = events.eventId
//                            viewModel.updatePlayers(events.teamId)
                            if(events.eventName == "GOAL"){
                                alertUpdatePlayer.value = true
                            } else if(events.eventName == "YELLOW_CARD"){
                                alertUpdateYellow.value = true
                            }
                        })
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = Base700
            )
        }
    }

}