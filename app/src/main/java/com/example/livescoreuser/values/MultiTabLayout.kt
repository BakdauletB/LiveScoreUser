package com.example.livescoresdu.uilibrary.values

import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.livescoresdu.data.model.CalendarPeriodModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import ffinbank.myfreedom.uilibrary.values.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.io.path.ExperimentalPathApi

@OptIn(ExperimentalComposeUiApi::class, ExperimentalPagerApi::class)
@Composable
fun MultiTabLayout(
    pagerState: PagerState,
    rewardPeriods: List<CalendarPeriodModel>,
    startPadding: Dp,
    endPadding: Dp
) {
    val scope = rememberCoroutineScope()
    val backgroundColor = remember {
        mutableStateOf(Color.Transparent)
    }
    val clickedInd = remember {
        mutableStateOf(0)
    }

   TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier,
        backgroundColor = Color.Transparent,
        divider = {
            TabRowDefaults.Divider(
                thickness = 0.dp,
                color = Color.Transparent
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .fillMaxHeight()
                    .padding(
                        start = if (pagerState.currentPage == 0) startPadding else 0.dp,
                        end = if (pagerState.currentPage == rewardPeriods.size - 1) endPadding else 0.dp
                    )
                    .clip(RoundedCornerShape(cornerRadius10))
                    .zIndex(1f),
            )
        }
    )
    {
        rewardPeriods.forEachIndexed { index, _ ->
            Tab(
                modifier = Modifier
                    .padding(
                        start = if (index == 0) startPadding else 0.dp,
                        end = if (index == rewardPeriods.size - 1) endPadding else 0.dp
                    )
                    .clip(RoundedCornerShape(cornerRadius8))
                    .zIndex(2f),
                content = {
//                    Row(
//                        modifier = Modifier
//                            .pointerInteropFilter {
//                                when (it.action) {
//                                    MotionEvent.ACTION_DOWN -> {
//                                        backgroundColor.value = Orange500
//                                        clickedInd.value = index
//                                    }
//                                    MotionEvent.ACTION_UP -> {
//                                        //Clicked
//                                        onClick(
//                                            scope = scope,
//                                            pagerState = pagerState,
//                                            index = index
//                                        )
//                                        backgroundColor.value = Color.Transparent
//                                        clickedInd.value = -1
//                                    }
//                                    MotionEvent.ACTION_CANCEL -> {
//                                        backgroundColor.value = Color.Transparent
//                                        clickedInd.value = -1
//                                    }
//                                }
//                                true
//                            }
//                        ,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
////                        AnimatedVisibility (color != null && color != Color.Transparent && pagerState.currentPage == index) {
////                            Row {
////                                Spacer(
////                                    modifier = Modifier
////                                        .size(8.dp)
////                                        .clip(CircleShape)
////                                        .background(color = color ?: Color.Transparent)
////                                )
////                                Spacer(modifier = Modifier.width(spacing8))
////                            }
////                        }
//                        Column(verticalArrangement = Arrangement.Center) {
////                            Text(
////                                text = list[index],
////                                color = if (pagerState.currentPage == index) Orange500 else Base50,
////                                fontSize = fontSize14,
////                                fontWeight = FontWeight.Medium,
////                                style = medium
////                            )
////                            repeat(rewardPeriods.size){ pos->
//                                Text(
//                                    text = rewardPeriods[index].getTitle(),
//                                    color = if (pagerState.currentPage == index) Orange500 else Base50,
//                                    fontSize = fontSize10,
//                                    fontWeight = FontWeight.Medium,
//                                    style = medium
//                                )
//                            Text(
//                                text = rewardPeriods[index].getDateAsString(),
//                                color = if (pagerState.currentPage == index) Orange500 else Base50,
//                                fontSize = fontSize10,
//                                fontWeight = FontWeight.Medium,
//                                style = medium
//                            )
//                        }
//
//                    }
                    Column(){
                        Text(
                            text = rewardPeriods[index].getTitleAsString(),
                            color = if (pagerState.currentPage == index) Orange500 else Base50,
                            fontSize = fontSize10,
                            fontWeight = FontWeight.Medium,
                            style = medium
                        )
                        Text(
                            text = rewardPeriods[index].getDateAsString(),
                            color = if (pagerState.currentPage == index) Orange500 else Base50,
                            fontSize = fontSize10,
                            fontWeight = FontWeight.Medium,
                            style = medium
                        )
                    }
                },
                selected = clickedInd.value == index,
                selectedContentColor = Base900,
                onClick = {
                    onClick(scope = scope, pagerState = pagerState, index = index)
//                    selectedPeriod.value = CalendarPeriodModel(rewardPeriods[index].type)
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
fun onClick(scope: CoroutineScope, pagerState: PagerState, index: Int) {
    scope.launch {
        pagerState.animateScrollToPage(index)
    }
}