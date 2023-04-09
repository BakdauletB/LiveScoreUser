package com.example.livescore.presentation.screens.matchdetails

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.livescore.R
import com.example.livescore.data.remote.dto.matchdetails.Lineup
import com.example.livescore.data.remote.dto.matchdetails.MatchEvent
import com.example.livescore.data.remote.dto.matches.News
import com.example.livescore.domain.models.MatchDetailsModel
import com.example.livescore.presentation.screens.matchdetails.components.TabScreenOne
import com.example.livescore.presentation.screens.matchdetails.components.TabScreenThree
import com.example.livescore.presentation.screens.matchdetails.components.TabScreenTwo
import com.example.livescore.presentation.screens.matchdetails.components.messageTypes.SummaryAction
import com.example.livescore.presentation.screens.matchdetails.components.messageTypes.SummaryType
import com.example.livescore.presentation.screens.matches.components.MatchesViewModel
import com.example.livescore.presentation.screens.matches.components.convertDate
import com.example.livescore.util.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MatchDetailsScreen(
    matchdetailsViewModel: MatchDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val matchesState = matchdetailsViewModel.state.value
    val context = LocalContext.current
//    val list = matchdetailsViewModel.listState
//    val lazyListState = rememberLazyListState(list.lastIndex)

//    LaunchedEffect(key1 = Unit,){
//        val match = match.find { it.news_id == matchId }
//        matchesViewModel.matchId =matchId
//    }

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
            Spacer(modifier = Modifier.height(spacing32))
            Row(
                modifier = Modifier.padding(spacing16),
                verticalAlignment = Alignment.CenterVertically
            ) {

                matchesState.matchdetails?.let { match ->

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
                                    painter = painterResource(id = R.drawable.barabar),
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
                                    painter = painterResource(id = R.drawable.sunkar),
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
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = spacing16, end = spacing16)
                    .background(shape = RoundedCornerShape(spacing12), color = Base900)
                    .border(
                        width = 1.5.dp,
                        color = Base700,
                        shape = RoundedCornerShape(spacing16)
                    )
                    .clip(RoundedCornerShape(spacing12))
                ) {
                    matchesState.matchdetails?.events?.forEach {
                        MatchDetailItem(events = it)
                    }
                }

        }
    }
}


//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(top = 40.dp)
//                ) {
//                    item {
//                        Column(modifier = Modifier.fillMaxSize()) {
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.SpaceAround,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = 6.dp)
//                            ) {
//                                Text(
//                                    text = "Premier league",
//                                    fontSize = 22.sp,
//                                    color = Color.White,
//                                    fontWeight = FontWeight.Bold
//                                )
//                            }
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.SpaceAround,
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(top = 10.dp)
//                            ) {
//                                Text(
//                                    text = "Week 10",
//                                    fontSize = 18.sp,
//                                    color = Color.LightGray,
//                                )
//                            }
//                            Spacer(modifier = Modifier.height(10.dp))
//                            Row(
//                                modifier = Modifier
//                                    .align(Alignment.CenterHorizontally)
//                                    .fillMaxSize()
//                                    .padding(start = 30.dp)
//                            ) {
//                                Column(
//                                    modifier = Modifier
//                                        .width(100.dp)
//                                        .padding(start = 20.dp)
//                                ) {
//
//                                    Row {
//                                        Spacer(modifier = Modifier.width(8.dp))
////                                        val image: Painter = R.drawable.barabar
////                                            rememberImagePainter(data = match.)
//                                        Image(
//                                            modifier = Modifier
//                                                .height(50.dp)
//                                                .width(50.dp)
//                                                .clip(RoundedCornerShape(8.dp)),
//                                            painter = painterResource(id = R.drawable.barabar),
//                                            alignment = Alignment.Center,
//                                            contentDescription = "",
//                                            contentScale = ContentScale.Crop
//                                        )
//                                    }
//
//                                    Spacer(modifier = Modifier.height(4.dp))
//
//                                    Text(
//                                        text = match.team1,
//                                        style = MaterialTheme.typography.body1,
//                                        color = Color.White,
//                                        fontSize = 15.sp
//                                    )
//                                    Text(
//                                        text = "     Home",
//                                        style = MaterialTheme.typography.body1,
//                                        color = Color.LightGray,
//                                        fontSize = 13.sp,
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
//                                Box(
//                                    modifier = Modifier
//                                        .width(100.dp)
//                                        .align(alignment = Alignment.CenterVertically)
//                                ) {
//                                    Row() {
//                                        val imaged: Painter =
//                                            rememberImagePainter(data = com.example.livescore.R.drawable.premier_logo)
//
//                                        Image(
//                                            modifier = Modifier
//                                                .height(100.dp)
//                                                .width(80.dp)
//                                                .clip(RoundedCornerShape(8.dp)),
//                                            painter = imaged,
//                                            alignment = Alignment.Center,
//                                            contentDescription = "",
//                                            contentScale = ContentScale.Crop
//                                        )
//                                    }
//
//                                    Column() {
//
//                                        Row(modifier = Modifier.fillMaxWidth()) {
//                                            Spacer(modifier = Modifier.width(3.dp))
//                                            Text(
//                                                text = " ${match.gameScore}",
//                                                style = MaterialTheme.typography.h1,
//                                                color = Color.White,
//                                                fontSize = 52.sp,
//                                                fontWeight = FontWeight.Bold,
//                                                modifier = Modifier.padding(top = 10.dp)
//                                            )
//                                        }
//
//                                        Row(
//                                            modifier = Modifier.padding(
//                                                32.dp,
//                                                1.dp,
//                                                1.dp,
//                                                1.dp
//                                            )
//                                        ) {
//                                            Box(
//                                                modifier =
//                                                Modifier
//                                                    .border(
//                                                        width = 1.dp,
//                                                        color = colorResource(id = com.example.livescore.R.color.pink),
//                                                        shape = RoundedCornerShape(16.dp)
//                                                    )
//                                                    .padding(10.dp)
//                                                    .height(16.dp)
//
//                                            ) {
//
//                                                    Text(
//                                                        text = "${match.dateAndTime?.let { convertDate(it, fromFormat = "yyyy-MM-dd'T'HH:mm:ss", toFormat = "HH:mm")}}",
//                                                        color = Color.White,
//                                                        textAlign = TextAlign.Center,
//                                                        style = MaterialTheme.typography.body2
//                                                    )
//
//                                            }
//                                        }
//                                    }
//                                }
//                                Column(
//                                    modifier = Modifier
//                                        .width(100.dp)
//                                        .padding(end = 12.dp)
//                                ) {
//                                    Row() {
//                                        Spacer(modifier = Modifier.width(8.dp))
////                                        val imagetwo: Painter =
////                                            rememberImagePainter(data = match.away_team?.logo)
//
//                                        Image(
//                                            modifier = Modifier
//                                                .height(50.dp)
//                                                .width(50.dp)
//                                                .clip(RoundedCornerShape(8.dp)),
//                                            painter = painterResource(id = R.drawable.sunkar),
//                                            alignment = Alignment.Center,
//                                            contentDescription = "",
//                                            contentScale = ContentScale.Crop
//                                        )
//                                    }
//
//                                    Spacer(modifier = Modifier.height(4.dp))
//                                    Text(
//                                        text = match.team2,
//                                        style = MaterialTheme.typography.body1,
//                                        color = Color.White,
//                                        fontSize = 15.sp
//                                    )
//                                    Text(
//                                        text = "     Away",
//                                        style = MaterialTheme.typography.body1,
//                                        color = Color.LightGray,
//                                        fontSize = 13.sp,
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
//                            }
//                        }
//                    }
//                }
//            }

//            TabScreen(
//                match.match_events!!,
//                match.home_team!!.team_id,
//                match.away_team!!.team_id,
//                match.lineups!!,
//                match,
//                match.match_id.toString(),
//            )
        //}

//        if (matchesState.error.isNotBlank()) {
//            Text(
//                text = matchesState.error,
//                color = MaterialTheme.colors.error,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .align(Alignment.CenterHorizontally)
//            )
//        }
//        if (matchesState.isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .size(33.dp)
//                    .padding(top = 100.dp)
//            )
//        }

























@Composable
fun Detail(news: News){
    Row() {
        Column(modifier = Modifier.weight(1f)) {
            Image(painter = painterResource(id = news.img_first), contentDescription = null, modifier = Modifier.size(
                spacing32))
            Text(text = news.title_first)
        }
        Column(modifier = Modifier.weight(1f)) {
            Row() {
                news.score_first.let { it1 -> Text(text = it1) }
                Text(text = "-")
                news.score_second.let { it1 -> Text(text = it1) }
            }
            Spacer(modifier = Modifier.height(spacing16))
            Text(text = "Full Time")
        }
    }
}

@Composable
fun SummartPushItem(
    modifier: Modifier,
    type: SummaryType
) {

    Row(modifier = modifier.padding(spacing16)) {
        Column(Modifier.weight(1f)) {
            type.time?.let { Text(text = it) }
            Spacer(modifier = Modifier.weight(1f))
            type.name?.let { Text(text = it) }
            Image(painter = painterResource((type as SummaryAction.Yellow).yellowImage), contentDescription = null)
        }
        Column() {
            type.scoreOne?.let { Text(text = it) }
            Text(text = "-")
            type.scoreSecond?.let { Text(text = it) }
            Image(painter = painterResource(id = (type as SummaryAction.Ball).ball),
                contentDescription = null )
            type.nameSecond?.let { Text(text = it) }
        }
    }

}

@ExperimentalPagerApi
@Composable
fun TabScreen(
    match: List<MatchEvent>,
    one: Int,
    two: Int,
    lineup: List<Lineup>,
    masa: MatchDetailsModel,
    matchId: String,
) {
    val pagerState = rememberPagerState(pageCount = 3)

//    Column(
//        modifier = Modifier.background(Color.White)
//    ) {
//        Tabs(pagerState = pagerState)
//        TabsContent(pagerState = pagerState, match, one, two, lineup, masa, matchId)
//    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {

    val list = listOf("Events", "Lineup", "Stats")

    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = colorResource(id = com.example.livescore.R.color.purple),
        contentColor = Color.White,
        divider = {
            TabRowDefaults.Divider(
                thickness = 3.dp,
                color = Color.White
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .width(20.dp),
                height = 3.dp,
                color = Color.Red
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        text = list[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },

            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    match: List<MatchEvent>,
    one: Int,
    two: Int,
    lineup: List<Lineup>,
    masa: MatchDetailsModel,
    matchId: String,
) {

    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> TabScreenOne(
                tabName = "This is a Match events Tab Layout",
                match = match,
                one = one,
                two = two
            )
            1 -> TabScreenTwo(
                tabName = "This is a Lineup tab Layout",
                lineup = lineup,
                one = one,
                two = two
            )
            2 -> TabScreenThree(tabName = "This is a stats tab Layout", stata = masa)
        }
    }
}

