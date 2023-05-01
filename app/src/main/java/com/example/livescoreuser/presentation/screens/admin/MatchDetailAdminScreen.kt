package com.example.livescoresdu.presentation.screens.admin

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.livescoresdu.presentation.screens.bundle.IdBundle
import com.example.livescoresdu.presentation.screens.detail.MatchDetailItem
import com.example.livescoresdu.uilibrary.values.*
import com.example.livescoreuser.R
import com.example.livescoreuser.presentation.viewmodels.MatchDetailAdminViewModel
import ffinbank.myfreedom.uilibrary.values.*
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MatchDetailAdminScreen(
    navController: NavController,
    onBackClick: () -> Unit,
    viewModel: MatchDetailAdminViewModel = getViewModel()
){
    val protocolId = IdBundle.id
    LaunchedEffect(key1 = Unit,){
        viewModel.loadMatches(protocolId)
    }



    val toolbarHeight = cardBottomSheetHeight
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetDefaultHeight = remember { screenHeight - toolbarHeight + 48.dp }

    val sheetHeight = remember {
        mutableStateOf(sheetDefaultHeight)
    }
    val endMatch = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val sheetHeightAnimated =
        animateFloatAsState(targetValue = sheetHeight.value.value, animationSpec = tween(200))
    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewModel.refreshing,
        onRefresh = { scope.launch {
            viewModel.refresh()
        } })
    if(viewModel.protocolState == UiStatus.SUCCESS){
        viewModel.stopRefreshing()
    }

        Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(
                state = pullRefreshState,
                enabled = !viewModel.refreshing
            )
            .background(color = colorResource(id = R.color.black))
    ) {

            Scaffold(
                topBar = {
                    Column() {
                        Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = null,
                            tint = Base700,
                            modifier = Modifier
                                .padding(start = spacing16, top = spacing16)
                                .clickable {
                                    if (viewModel.protocol.value?.gameState == "STARTED") {
                                        navController.popBackStack(
                                            MainDestinations.MAIN.destination,
                                            false)
                                    }
                                    else {
                                        onBackClick()
                                    }
                                }
                        )
                        Spacer(modifier = Modifier.height(spacing10))
                        Row(
                            modifier = Modifier.padding(spacing16),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(spacing16)
                                    .background(
                                        shape = RoundedCornerShape(cornerRadius12),
                                        color = itemColor
                                    )
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
                                        Spacer(modifier = Modifier.height(spacing6))
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
                                                text = "${
                                                    viewModel.protocol.value?.gameScore?.substring(
                                                        0,
                                                        1
                                                    )
                                                }",
                                                fontSize = fontSize22,
                                                color = Base400,
                                                style = semiBold,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            Spacer(modifier = Modifier.width(spacing6))
                                            Text(
                                                text = "-",
                                                fontSize = fontSize22,
                                                color = Base400,
                                            )
                                            Spacer(modifier = Modifier.width(spacing6))
                                            Text(
                                                text = "${
                                                    viewModel.protocol.value?.gameScore?.substring(
                                                        2,
                                                        3
                                                    )
                                                }",
                                                fontSize = fontSize22,
                                                color = Base400,
                                                style = semiBold,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(spacing6))
                                        Text(
                                            text = if (viewModel.protocol.value?.gameState == "STARTED") "LIVE" else "LIVE",
                                            fontSize = fontSize11,
                                            color = Orange500,
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
                                        Spacer(modifier = Modifier.height(spacing6))
                                        Text(
                                            text = viewModel.protocol.value?.team2.orEmpty(),
                                            style = MaterialTheme.typography.body1,
                                            color = Color.White,
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
                bottomBar = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(spacing16))

                        Spacer(modifier = Modifier.height(spacing16))
                    }

                },

                backgroundColor = Base900
            ) {
//                Box(modifier = Modifier.fillMaxSize()) {
//                    Box(modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.TopCenter){

                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = spacing16, end = spacing16, bottom = spacing40)
                                    .background(
                                        shape = RoundedCornerShape(spacing12),
                                        color = Base900
                                    )
                                    .border(
                                        width = 1.5.dp,
                                        color = Base700,
                                        shape = RoundedCornerShape(spacing16)
                                    )
                                    .clip(RoundedCornerShape(spacing12))
                            ) {
                                item {
                                    IdBundle.team1Id = viewModel.protocol.value?.team1Id ?: 0
                                    IdBundle.team2Id = viewModel.protocol.value?.team2Id ?: 0
                                    viewModel.protocol.value?.events?.forEach {
                                        MatchDetailItem(events = it,
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(spacing40))
                        //}
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.BottomCenter
//                    ) {
//
//                    }
                //}
                it.calculateBottomPadding()
            }
            PullRefreshIndicator(
                refreshing = viewModel.refreshing,
                state = pullRefreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = spacing40),
                scale = true
            )
    }
    if(endMatch.value){
        navController.popBackStack(
            MainDestinations.MAIN.destination,
            false)
    }





}
@Composable
fun ButtonTeamOneItem(
    alert: MutableState<Boolean>,
    alertYellow: MutableState<Boolean>,
    alertRed: MutableState<Boolean>,
    viewModel: MatchDetailAdminViewModel = getViewModel()

){
    Column(modifier = Modifier.padding(start = spacing16)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Base50,
                shape = RoundedCornerShape(cornerRadius12)
            )
            .clip(RoundedCornerShape(cornerRadius12))
            .clickable {
                alert.value = true
                viewModel.team1.value = viewModel.protocol.value?.team1Id
                viewModel.team1.value?.let { viewModel.loadPlayers(it) }

            }
            .padding(spacing6)) {
            Text(text = "GOAL")
        }
        Spacer(modifier = Modifier.height(spacing12))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Base50,
                    shape = RoundedCornerShape(cornerRadius12)
                )
                .clip(RoundedCornerShape(cornerRadius12))
                .clickable {
                    alertYellow.value = true
                    viewModel.team1.value = viewModel.protocol.value?.team1Id
                    viewModel.team1.value?.let { viewModel.loadPlayers(it) }

                }
                .padding(spacing6)
        ) {
            Text(text = "YELLOW CARD")
        }
        Spacer(modifier = Modifier.height(spacing12))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Base50,
                    shape = RoundedCornerShape(cornerRadius12)
                )
                .clip(RoundedCornerShape(cornerRadius12))
                .clickable {
                    alertRed.value = true
                    viewModel.team1.value = viewModel.protocol.value?.team1Id
                    viewModel.team1.value?.let { viewModel.loadPlayers(it) }

                }
                .padding(spacing6)
        ) {
            Text(text = "RED CARD")
        }
    }

}
@Composable
fun ButtonTeamTwoItem(
    alert: MutableState<Boolean>,
    alertYellow: MutableState<Boolean>,
    alertRed: MutableState<Boolean>,
    viewModel: MatchDetailAdminViewModel = getViewModel()

){
    Column(modifier = Modifier.padding(start = spacing16)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Base50,
                    shape = RoundedCornerShape(cornerRadius12)
                )
                .clip(RoundedCornerShape(cornerRadius12))
                .clickable {
                    alert.value = true
                    viewModel.team2.value = viewModel.protocol.value?.team2Id
                    viewModel.team2.value?.let { viewModel.loadPlayersTwo(it) }
                }
                .padding(spacing6)
        ) {
            Text(text = "GOAL")
        }
        Spacer(modifier = Modifier.height(spacing12))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Base50,
                    shape = RoundedCornerShape(cornerRadius12)
                )
                .clip(RoundedCornerShape(cornerRadius12))
                .clickable {
                    alertYellow.value = true
                    viewModel.team2.value = viewModel.protocol.value?.team2Id
                    viewModel.team2.value?.let { viewModel.loadPlayersTwo(it) }

                }
                .padding(spacing6)
        ) {
            Text(text = "YELLOW CARD")
        }
        Spacer(modifier = Modifier.height(spacing12))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Base50,
                    shape = RoundedCornerShape(cornerRadius12)
                )
                .clip(RoundedCornerShape(cornerRadius12))
                .clickable {
                    alertRed.value = true
                    viewModel.team2.value = viewModel.protocol.value?.team2Id
                    viewModel.team2.value?.let { viewModel.loadPlayersTwo(it) }
                }
                .padding(spacing6)
        ) {
            Text(text = "RED CARD")
        }
    }


}