package com.example.livescore.presentation.screens.scorers.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.livescoreuser.presentation.viewmodels.ScorersViewModel
import ffinbank.myfreedom.uilibrary.values.*
import org.koin.androidx.compose.getViewModel

@Composable
fun ScorersScreen(navController: NavController,
    viewModel: ScorersViewModel = getViewModel()) {

    val alert = remember {
        mutableStateOf(false)
    }
    val matchDetails = viewModel.protocol.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = com.example.livescoreuser.R.color.black))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            Icon(
                painter = painterResource(id = com.example.livescoreuser.R.drawable.ic_back_arrow),
                contentDescription = null,
                tint = Base700,
                modifier = Modifier
                    .padding(start = spacing16, top = spacing16)
                    .clickable { }
            )
            Spacer(modifier = Modifier.height(spacing32))
            Row(
                modifier = Modifier.padding(spacing16),
                verticalAlignment = Alignment.CenterVertically
            ) {

                matchDetails?.let { match ->

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing16)
                            .background(shape = RoundedCornerShape(cornerRadius12), color = Base700)
                            .clip(RoundedCornerShape(cornerRadius16))
                            .padding(spacing20),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = spacing32, end = spacing32),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column() {
                                Image(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(50.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    painter = painterResource(id = com.example.livescoreuser.R.drawable.barabar),
                                    alignment = Alignment.Center,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(spacing6))
                                Text(
                                    text = match.team1,
                                    style = MaterialTheme.typography.body1,
                                    color = Color.White,
                                    fontSize = 15.sp
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = match.gameScore,
                                fontSize = fontSize22,
                                color = Base50,
                                modifier = Modifier
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Column() {
                                Image(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(50.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    painter = painterResource(id = com.example.livescoreuser.R.drawable.sunkar),
                                    alignment = Alignment.Center,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(spacing6))
                                Text(
                                    text = match.team2,
                                    style = MaterialTheme.typography.body1,
                                    color = Color.White,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(spacing16))
//               if(match.events[0].eventName == "GOAL"){
//
//               }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing16)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing16)
                        .background(color = Base50, shape = RoundedCornerShape(cornerRadius12))
                        .clip(RoundedCornerShape(cornerRadius12))
                        .clickable {
                            alert.value = true
                        }
                        .padding(spacing10)) {
                        Text(text = "GOAL")
                    }
                    Spacer(modifier = Modifier.height(spacing10))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing16)
                            .background(color = Base50, shape = RoundedCornerShape(cornerRadius12))
                            .clip(RoundedCornerShape(cornerRadius12))
                            .padding(spacing10)
                    ) {
                        Text(text = "YELLOW CARD")
                    }
                    Spacer(modifier = Modifier.height(spacing10))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing16)
                            .background(color = Base50, shape = RoundedCornerShape(cornerRadius12))
                            .clip(RoundedCornerShape(cornerRadius12))
                            .padding(spacing10)
                    ) {
                        Text(text = "RED CARD")
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing16)
                            .background(color = Base50, shape = RoundedCornerShape(cornerRadius12))
                            .clip(RoundedCornerShape(cornerRadius12))
                            .padding(spacing10)
                    ) {
                        Text(text = "GOAL")
                    }
                    Spacer(modifier = Modifier.height(spacing10))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing16)
                            .background(color = Base50, shape = RoundedCornerShape(cornerRadius12))
                            .clip(RoundedCornerShape(cornerRadius12))
                            .padding(spacing10)
                    ) {
                        Text(text = "YELLOW CARD")
                    }
                    Spacer(modifier = Modifier.height(spacing10))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing16)
                            .background(color = Base50, shape = RoundedCornerShape(cornerRadius12))
                            .clip(RoundedCornerShape(cornerRadius12))
                            .padding(spacing10)
                    ) {
                        Text(text = "RED CARD")
                    }
                }
            }
        }
    }


}















//    Box(modifier = Modifier.fillMaxSize()) {
//        Column(modifier = Modifier.fillMaxSize()) {
//            Image(
//                painter = painterResource(id = R.drawable.three), contentDescription = "",
//                modifier = Modifier
//                    .height(200.dp)
//                    .fillMaxWidth().fillMaxHeight(),
//                contentScale = ContentScale.Crop
//            )
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.LightGray)
//            ) {
//                Row(modifier = Modifier.padding(10.dp)) {
//                    Text(
//                        text = "Pos",
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(start = 10.dp)
//                    )
//                    Spacer(modifier = Modifier.width(40.dp))
//                    Text(
//                        text = "Player name",
//                        fontSize = 14.sp
//                    )
//
//                    Spacer(modifier = Modifier.width(120.dp))
//                    Text(
//                        text = "Goals",
//                        fontSize = 14.sp
//                    )
//                }
//            }
//            LazyColumn() {
//                items(scorerstate.scorer) { scorer ->
//                    ScorerListItem(
//                        scorer = scorer,
//                    )
//                }
//            }
        //}
//        if (scorerstate.error.isNotBlank()) {
//            Text(
//                text = scorerstate.error,
//                color = MaterialTheme.colors.error,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .align(Alignment.Center)
//            )
//        }
//        if (scorerstate.isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center),
//                color = colorResource(
//                    id = R.color.purple
//                )
//            )
//        }
    ///}
