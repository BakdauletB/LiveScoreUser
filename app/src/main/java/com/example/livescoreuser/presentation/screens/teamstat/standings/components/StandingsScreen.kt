package com.example.livescore.presentation.screens.standings.components

import MulTabLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.livescore.util.*
import com.example.livescoresdu.presentation.viewmodels.MatchesViewModel
import com.example.livescoreuser.R
import com.example.livescoreuser.presentation.screens.teamstat.standings.components.StandingsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ffinbank.myfreedom.uilibrary.values.Base50
import ffinbank.myfreedom.uilibrary.values.Base700
import ffinbank.myfreedom.uilibrary.values.cornerRadius12
import ffinbank.myfreedom.uilibrary.values.fontSize13
import ffinbank.myfreedom.uilibrary.values.fontSize16
import ffinbank.myfreedom.uilibrary.values.medium
import ffinbank.myfreedom.uilibrary.values.semiBold
import ffinbank.myfreedom.uilibrary.values.spacing10
import ffinbank.myfreedom.uilibrary.values.spacing16
import ffinbank.myfreedom.uilibrary.values.spacing24
import ffinbank.myfreedom.uilibrary.values.spacing32
import ffinbank.myfreedom.uilibrary.values.spacing6
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StandingScreen(
    viewModel: StandingsViewModel = getViewModel(),
    onBackClick: () -> Unit
) {
    val pagerState = rememberPagerState(5)
    val scope = rememberCoroutineScope()

    val matchesGoals = viewModel.goals
    val points = viewModel.groupPoints
    val playersGoals = viewModel.playerGoals
    val assists = viewModel.assists
    val redCards = viewModel.redCards
    val yellowCards = viewModel.yellowCards
    val teamRedCards = viewModel.teamRedCards
    val teamYellowCards = viewModel.teamYellowCards
    LaunchedEffect(key1 = scope){
        viewModel.getAssists()
        viewModel.getGoals()
//        viewModel.getPoints()
        viewModel.getGroupPoints()
        viewModel.getPlayerGoals()
        viewModel.getRedCards()
        viewModel.getYellowCards()
        viewModel.getTeamRedCards()
        viewModel.getTeamYellowCards()
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
            Spacer(modifier = Modifier.height(spacing32))
            Row(
                modifier = Modifier.padding(spacing16),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                    tint = Base700,
                    modifier = Modifier.clickable { onBackClick() }
                )

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Column(modifier = Modifier
                        .padding(start = spacing10)
                        .background(color = Base50, RoundedCornerShape(cornerRadius12))
                        .padding(spacing6),) {
                        Image(painter = painterResource(id = R.drawable.sdu_logo),
                            contentDescription = null,
                            modifier = Modifier
                                .width(spacing32)
                                .height(spacing24))
                    }
                    Spacer(modifier = Modifier.width(spacing16))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "SDU Football league",
                            color = Base50,
                            fontSize = fontSize16,
                            fontWeight = FontWeight.SemiBold,
                            style = semiBold)
                        Spacer(modifier = Modifier.height(spacing6))
                        Text(text = "Almaty",
                            color = Base700,
                            fontSize = fontSize13,
                            fontWeight = FontWeight.Medium,
                            style = medium)

                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                MulTabLayout(
                    pagerState = pagerState,
                    list = listOf(
                        "Overview",
                        "Matches",
                        "Table",
                        "Player Stats",
                        "Team Stats",
                    ),
                    list1 = listOf(
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    startPadding = spacing16,
                    endPadding = spacing16
                )
            }
            HorizontalPager(state = pagerState,
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.Top,) { page ->
                when (page){
                    0 -> Overview()
                    1 -> Matches()
                    2 -> Table(points)
                    3 -> PlayerStats(playersGoals, assistsGoalState = assists, redCardState = redCards, yellowCardState = yellowCards)
                    4 -> TeamStats(matchesGoals, teamRedCardsState = teamRedCards, teamYellowCardsState = teamYellowCards)
                }
            }

        }
    }

//    if (matchesGoals.isLoading) {
//        CircularProgressIndicator(
//            color = colorResource(
//                id = R.color.purple
//            )
//        )
//    }
}










//    val state = viewModel.state.value
//    val isRefreshing by viewModel.isRefresh.collectAsState()

//    Box(
//        modifier = Modifier
//            // .background()
//            .fillMaxSize()
//            .padding(0.dp)
//    ) {
//        Column {
//            SwipeRefresh(
//                state = rememberSwipeRefreshState(true),
//                onRefresh = { }
//            ) {
//
//                Column() {
//
//                    Image(
//                        painter = painterResource(id = R.drawable.banner), contentDescription = "",
//                        modifier = Modifier
//                            .height(200.dp)
//                            .fillMaxWidth(),
//                        contentScale = ContentScale.FillWidth
//                    )
//
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(Color.LightGray)
//                    ) {
//                        Row(modifier = Modifier.padding(10.dp)) {
//                            Text(
//                                text = "Pos",
//                                fontSize = 14.sp,
//                                modifier = Modifier.padding(start = 10.dp)
//                            )
//                            Spacer(modifier = Modifier.width(23.dp))
//                            Text(
//                                text = "Club",
//                                fontSize = 14.sp
//                            )
//                            Spacer(modifier = Modifier.width(30.dp))
//                            Text(
//                                text = "P",
//                                fontSize = 14.sp,
//                                modifier = Modifier.padding(start = 20.dp)
//                            )
//                            Spacer(modifier = Modifier.width(20.dp))
//                            Text(
//                                text = "W",
//                                fontSize = 14.sp,
//                                modifier = Modifier.padding(start = 14.dp)
//                            )
//                            Spacer(modifier = Modifier.width(20.dp))
//                            Text(
//                                text = "D",
//                                fontSize = 14.sp
//                            )
//                            Spacer(modifier = Modifier.width(20.dp))
//                            Text(
//                                text = "L",
//                                fontSize = 14.sp,
//                                modifier = Modifier.padding(start = 10.dp)
//                            )
//                            Spacer(modifier = Modifier.width(20.dp))
//                            Text(
//                                text = "GD",
//                                fontSize = 14.sp
//                            )
//                            Spacer(modifier = Modifier.width(20.dp))
//                            Text(
//                                text = "Pts",
//                                fontSize = 14.sp
//                            )
//                        }
//                    }
//
////                    LazyColumn() {
////                        items(state.standings) { standings ->
////                            StandingsListItem(
////                                standings = standings,
////                            )
////                        }
////                    }
//                }
//            }
//        }
////        if (state.error.isNotBlank()) {
////            Text(
////                text = state.error,
////                color = MaterialTheme.colors.error,
////                textAlign = TextAlign.Center,
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .padding(horizontal = 20.dp)
////                    .align(Alignment.Center)
////            )
////        }
////        if (state.isLoading) {
////            CircularProgressIndicator(
////                modifier = Modifier.align(Alignment.Center),
////                color = colorResource(
////                    id = R.color.purple
////                )
////            )
////        }
//    }

