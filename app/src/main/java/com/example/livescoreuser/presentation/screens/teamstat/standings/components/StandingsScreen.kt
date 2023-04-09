package com.example.livescore.presentation.screens.standings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.livescore.R
import com.example.livescore.presentation.Screen
import com.example.livescore.presentation.screens.matches.components.CalendarPeriodModel
import com.example.livescore.presentation.screens.matches.components.MatchesViewModel
import com.example.livescore.presentation.screens.standings.StandingsViewModel
import com.example.livescore.util.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StandingScreen(
    viewModel: StandingsViewModel = getViewModel(),
    bottomNavHide: (Boolean) -> Unit,
    matchesViewModel: MatchesViewModel = getViewModel(),
    onBackClick: () -> Unit
) {
    val pagerState = rememberPagerState(5)
    val matchesGoals = viewModel.state.value
    val points = viewModel.pointsState.value
    val playersGoals = viewModel.playerState.value
    val assists = viewModel.assistState.value
    val redCards = viewModel.redcardState.value
    val yellowCards = viewModel.yellowCardState.value
    val teamRedCards = viewModel.teamRedCardsState.value
    val teamYellowCards = viewModel.teamYellowCardsState.value

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
                            modifier = Modifier.width(spacing32).height(spacing24))
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
                verticalAlignment = Alignment.Top) { page ->
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
    if (matchesGoals.error.isNotBlank()) {
        Text(
            text = matchesGoals.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
    if (matchesGoals.isLoading) {
        CircularProgressIndicator(
            color = colorResource(
                id = R.color.purple
            )
        )
    }
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

