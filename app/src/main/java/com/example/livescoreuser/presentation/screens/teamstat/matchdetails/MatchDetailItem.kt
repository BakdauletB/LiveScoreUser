package com.example.livescore.presentation.screens.matchdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.livescore.data.remote.dto.Events
import com.example.livescore.util.*

@Composable
fun MatchDetailItem(events: Events){
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(spacing16)) {
            Row(modifier = Modifier.weight(0.3f)) {
                Text(text = events.minute.toString(), color = Base50)
                Spacer(modifier = Modifier.width(spacing6))
                if(events.teamId == 1){
                    Column() {
                        if(events.eventName == "GOAL"){
                            Row() {
//                                Text(text = events.minute.toString(), color = Base50)
//                                Spacer(modifier = Modifier.width(spacing6))
                                Spacer(modifier = Modifier.weight(1f))
                                Text(text = events.playerName.substring(0,9), color = Base50)
                                Spacer(modifier = Modifier.width(spacing6))
                                Image(painter = painterResource(id = com.example.livescore.R.drawable.ball),
                                    contentDescription =null,
                                    modifier = Modifier.size(spacing20))
                            }
                        }
//                        if(events.eventName == "ASSIST"){
//                            Text(text = events.playerName, color = Base50)
//                        }
                    }
                    Spacer(modifier = Modifier.height(spacing16))
                    if(events.eventName == "YELLOW_CARD"){
//                        Text(text = events.minute.toString(), color = Base50)
//                        Spacer(modifier = Modifier.width(spacing6))
                        Text(text = events.playerName.substring(0,9), color = Base50)
                        Spacer(modifier = Modifier.width(spacing6))
                        Image(painter = painterResource(id = com.example.livescore.R.drawable.yellowcard),
                            contentDescription = null,
                            modifier = Modifier.size(spacing20))
                    }
                    Spacer(modifier = Modifier.height(spacing16))
                    if(events.eventName == "RED_CARD"){
//                        Text(text = events.minute.toString(), color = Base50)
//                        Spacer(modifier = Modifier.width(spacing6))
                        Text(text = events.playerName.substring(0,9), color = Base50)
                        Spacer(modifier = Modifier.width(spacing6))
                        Image(painter = painterResource(id = com.example.livescore.R.drawable.red_card),
                            contentDescription = null,
                            modifier = Modifier.size(spacing20))
                    }
                }
                Spacer(modifier = Modifier.width(spacing16))
            }
            Spacer(modifier = Modifier.width(spacing16))
            Text(text = events.gameScore, color = Base50,)
            Row(modifier = Modifier.weight(0.3f)) {
                if(events.teamId == 2){
                    Spacer(modifier = Modifier.width(spacing16))
                    Column() {
                        if(events.eventName == "GOAL"){
                            Row() {
//                                Text(text = events.minute.toString(), color = Base50)
//                                Spacer(modifier = Modifier.weight(1f))
//                                Text(text = events.gameScore, color = Base50)
//                                Spacer(modifier = Modifier.width(spacing6))
                                Image(painter = painterResource(id = com.example.livescore.R.drawable.ball),
                                    contentDescription =null,
                                    modifier = Modifier.size(spacing20))
                            Spacer(modifier = Modifier.width(spacing6))
                                Text(text = events.playerName.substring(0,9), color = Base50)
                            }
                        }
//                    if(events.eventName == "ASSIST"){
//                        Row() {
//                            Spacer(modifier = Modifier.weight(1f))
//                            Text(text = events.playerName, color = Base50)
//                        }
//                    }
                    }
                    Spacer(modifier = Modifier.height(spacing16))

                    if(events.eventName == "RED_CARD"){
//                        Text(text = events.minute.toString(), color = Base50)
//                        Spacer(modifier = Modifier.weight(1f))
                        Image(painter = painterResource(id = com.example.livescore.R.drawable.red_card),
                            contentDescription = null,
                            modifier = Modifier.size(spacing20))
                        Spacer(modifier = Modifier.width(spacing6))
                        Text(text = events.playerName.substring(0,9), color = Base50)
                    }
                    Spacer(modifier = Modifier.height(spacing16))
                    if(events.eventName == "YELLOW_CARD"){
//                        Text(text = events.minute.toString(), color = Base50)
//                        Spacer(modifier = Modifier.weight(1f))
                        Image(painter = painterResource(id = com.example.livescore.R.drawable.yellowcard),
                            contentDescription = null,
                            modifier = Modifier.size(spacing20))
                        Spacer(modifier = Modifier.width(spacing6))
                        Text(text = events.playerName.substring(0,9), color = Base50)
                    }
                }
            }

        }
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            color = Base700
        )
    }

}