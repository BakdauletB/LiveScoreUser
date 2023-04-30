package com.example.livescoresdu.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.livescoresdu.data.model.convertDate
import com.example.livescoresdu.presentation.screens.bundle.IdBundle
import com.example.livescoresdu.presentation.viewmodels.MatchesDetailViewModel
import com.example.livescoresdu.uilibrary.values.CustomButton
import com.example.livescoresdu.uilibrary.values.CustomButtonText
import com.example.livescoresdu.uilibrary.values.HomeDestinations
import com.example.livescoreuser.R
import ffinbank.myfreedom.uilibrary.values.*
import org.koin.androidx.compose.getViewModel

@Composable
fun MatchesDetailScreen(
    navController: NavController,
    viewModel: MatchesDetailViewModel = getViewModel(),
    onBackClick: () -> Unit
){
    val context = LocalContext.current
    val start = remember {
        mutableStateOf(false)
    }
    val protocolId = IdBundle.id
    LaunchedEffect(key1 = Unit,){
        viewModel.loadMatches(protocolId)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.black))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = null,
                tint = Base700,
                modifier = Modifier
                    .padding(start = spacing16, top = spacing16)
                    .clickable { onBackClick() }
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(spacing16))
            Row(
                modifier = androidx.compose.ui.Modifier.padding(spacing16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing16)
                        .background(shape = RoundedCornerShape(cornerRadius12), color = itemColor)
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
                                painter = rememberAsyncImagePainter(viewModel.protocol.value?.team1Logo),
                                alignment = Alignment.Center,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = androidx.compose.ui.Modifier.height(spacing6))
                            Text(
                                text = viewModel.protocol.value?.team1.orEmpty(),
                                style = MaterialTheme.typography.body1,
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Row {
                                Text(
                                    text = "${viewModel.protocol.value?.gameScore?.substring(0,1)}",
                                    fontSize = fontSize22,
                                    color = Base400,
                                    style = semiBold,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.width(spacing6))
                                Text(text = "-",
                                    fontSize = fontSize22,
                                    color = Base400,)
                                Spacer(modifier = Modifier.width(spacing6))
                                Text(
                                    text = "${viewModel.protocol.value?.gameScore?.substring(2,3)}",
                                    fontSize = fontSize22,
                                    color = Base400,
                                    style = semiBold,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Spacer(modifier = Modifier.height(spacing6))
                            Text(
                                text = "${viewModel.protocol.value?.dateAndTime?.let { convertDate(it, fromFormat = "yyyy-MM-dd'T'HH:mm:ss", toFormat = "HH:mm")}}",
                                fontSize = fontSize11,
                                color = Base700,
                                style = regular,
                                modifier = Modifier
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Column() {
                            Image(
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                painter = rememberAsyncImagePainter(viewModel.protocol.value?.team2Logo),
                                alignment = Alignment.Center,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = androidx.compose.ui.Modifier.height(spacing6))
                            Text(
                                text = viewModel.protocol.value?.team2.orEmpty(),
                                style = MaterialTheme.typography.body1,
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
                Spacer(modifier = androidx.compose.ui.Modifier.height(spacing16))
//
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = itemColor), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(spacing100))
                CustomButton(buttonColors = ButtonDefaults.buttonColors(backgroundColor = Orange500),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = spacing16, end = spacing16)
                        .clickable {
                            start.value = true
                        },
                    content = {
                        CustomButtonText(
                            text = "Start",
                            color = Base900
                        )
                    }) {
                        viewModel.loadStartGame(protocolId)
                    navController.navigate("${HomeDestinations.MATCH_DETAIL_ADMIN}/${viewModel.protocol.value?.protocolId}")

                }
                Spacer(modifier = Modifier.height(spacing10))
                CustomButton(buttonColors = ButtonDefaults.buttonColors(backgroundColor = Base300),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = spacing16, end = spacing16), content = {
                        CustomButtonText(
                            text = "Back",
                            color = Base900
                        )
                    }) {
                    navController.popBackStack()
                }
            }
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = spacing16, end = spacing16)
//                    .background(shape = RoundedCornerShape(spacing12), color = Base900)
//                    .border(
//                        width = 1.5.dp,
//                        color = Base700,
//                        shape = RoundedCornerShape(spacing16)
//                    )
//                    .clip(RoundedCornerShape(spacing12))
//            ) {
//                viewModel.protocol.value?.events?.forEach {
//                    MatchDetailItem(events = it)
//                }
//            }

        }
    }


}