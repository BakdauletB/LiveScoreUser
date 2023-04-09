package com.example.livescore.presentation.screens.matches

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.livescore.R
import com.example.livescore.data.remote.dto.matches.News
import com.example.livescore.presentation.FilterContent
import com.example.livescore.presentation.Screen
import com.example.livescore.presentation.screens.CalendarBottomSheet
import com.example.livescore.presentation.screens.matches.components.*
import com.example.livescore.util.*
import com.example.livescore.util.Constants.FILTER_CONTENT_LIST
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun MatchesScreen(
    navController: NavController,
    bottomNavHide: (Boolean) -> Unit,
    matchesViewModel: MatchesViewModel = hiltViewModel()
) {
//    val matchesState = matchesViewModel.list.value
    val gameState = matchesViewModel.gameState.value
    val gameState1 = matchesViewModel.gameState1.value
    val gameState2 = matchesViewModel.gameState2.value
    val gameState3 = matchesViewModel.gameState3.value
    val gameState4 = matchesViewModel.gameState4.value
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(matchesViewModel.incomePeriods.size)
    var openCalendarBottomSheet by remember {
        mutableStateOf(false)
    }
//    if(!gameState.isLoading){
//        matchesViewModel.stopRefreshing()
//    }
    val isRefreshing by matchesViewModel.isRefresh.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = matchesViewModel.refreshing,
        onRefresh = { scope.launch {
            matchesViewModel.refresh()
        } })


    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(
            state = pullRefreshState,
            enabled = !matchesViewModel.refreshing
        )
            .background(color = colorResource(id = R.color.black))
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = { matchesViewModel.refresh() }
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            Spacer(modifier = Modifier.height(spacing32))
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = spacing6, end = spacing6),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.2f)
                        .background(color = Base200, shape = RoundedCornerShape(cornerRadius16))
                        .clip(RoundedCornerShape(cornerRadius12))
                        .padding(spacing6),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "LIVE",
                        style = semiBold,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = fontSize10,
                    )
                }
                Row(modifier = Modifier.weight(1f)) {
                    MultiTabLayout(
                        pagerState = pagerState,
                        rewardPeriods = matchesViewModel.incomePeriods,
                        startPadding = spacing4,
                        endPadding = spacing4
                    )
                }
                Image(painter = painterResource(id = R.drawable.calendar),
                    contentDescription = null,
                    modifier = Modifier
                        .width(spacing32)
                        .height(spacing32)
                        .weight(0.1f)
                        .clickable {
                            openCalendarBottomSheet = true
                        })

            }
            HorizontalPager(
                state = pagerState, modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.Top
            ) { page ->
                when (page) {
                    0 -> ListGame(navController = navController, gameState = gameState)
                    1 -> ListGame(navController = navController, gameState = gameState1)
                    2 -> ListGame(navController = navController, gameState = gameState2)
                    3 -> ListGame(navController = navController, gameState = gameState3)
                    4 -> ListGame(navController = navController, gameState = gameState4)
                }
            }
        }
        }

    }

    if(openCalendarBottomSheet){
        bottomNavHide(true)
    }
    CalendarBottomSheet(
        openCalendarBottomSheet = openCalendarBottomSheet,
        onOpenCalendarBottomSheet = {
            openCalendarBottomSheet = it
        },
        onBottomSheetHide = { date1,  ->
            if (date1 != null ) {
//                matchesViewModel.selectedPeriod.value = CalendarPeriodModel(Periods.SELECT_PERIOD)
                matchesViewModel.setSelectedPeriod(date1,)
            }
            openCalendarBottomSheet = false
        }
    )

}

@Composable
fun ListGame(navController: NavController, gameState: GameDateState){
    Column() {
        Spacer(modifier = Modifier.height(spacing10))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .background(color = Base400)
            .padding(spacing1))
        Spacer(modifier = Modifier.height(spacing16))
        Row(modifier = Modifier.clickable {
            navController.navigate(Screen.StandingScreen.route)
        }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
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


        LazyColumn(modifier = Modifier
            .background(color = Base900)){
            items(gameState.game){ game ->
                MatchItem(modifier = Modifier, gameDateResponse = game) {
                    navController.navigate(Screen.MatchDetailsScreen.route + "/${game.protocolId}")

                }
            }
        }
    }
}
@Composable
fun HorizontalScroll(items: List<News>){
    Column() {
        repeat(items.size){
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = items[it].date!!)
                Column() {
                    Image(painter = painterResource(id = R.drawable.barabar), contentDescription = null)
                }
            }
        }
    }
}

@Composable
fun FilterOptionsComponent() {
    val filterOptions = FILTER_CONTENT_LIST
    LazyRow(
        Modifier.padding(top = 15.dp, start = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(filterOptions.size) {
            ChipComponent(filter = filterOptions[it])
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipComponent(filter: FilterContent) {
    val contentColor = filter.contentColor
    val chipBackground = filter.backgroundColor
    val filterText = filter.filterText
    val logo = filter.logo
    Chip(
        onClick = { /*TODO*/ },
        colors = ChipDefaults.chipColors(
            contentColor = contentColor,
            backgroundColor = colorResource(id = chipBackground)
        ),
        shape = RoundedCornerShape(60.dp)
    ) {
        Row(modifier = Modifier.height(40.dp)) {
            val image: Painter = rememberImagePainter(data = logo)

            Image(
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterVertically),
                painter = image,
                alignment = Alignment.Center,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = filterText, modifier = Modifier.align(Alignment.CenterVertically))
        }
    }
}

@Composable
fun LoadingGif(
    context: Context,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.bouncel).apply(block = {
                size(Size.ORIGINAL)
            }).build(),
            imageLoader = context.gifLoader()
        ),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
    )
}
