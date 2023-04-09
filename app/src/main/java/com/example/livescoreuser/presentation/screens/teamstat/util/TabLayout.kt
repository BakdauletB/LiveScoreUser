package com.example.livescore.util

import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalPagerApi
@Composable
fun TabLayout(
    pagerState: PagerState,
    list: List<String>,
    color: Color? = null,
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

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier,
        edgePadding = 0.dp,
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
                        end = if (pagerState.currentPage == list.size - 1) endPadding else 0.dp
                    )
                    .clip(RoundedCornerShape(cornerRadius10))
                .zIndex(1f),
            color = Base50            )
        }
    )
    {
        list.forEachIndexed { index, _ ->
            Tab(
                modifier = Modifier
                    .padding(
                        start = if (index == 0) startPadding else 0.dp,
                        end = if (index == list.size - 1) endPadding else 0.dp
                    )
                    .clip(RoundedCornerShape(cornerRadius8))
                    .zIndex(2f),
                content = {
                    Row(
                        modifier = Modifier
                            .pointerInteropFilter {
                                when (it.action) {
                                    MotionEvent.ACTION_DOWN -> {
                                        backgroundColor.value = Base50
                                        clickedInd.value = index
                                    }
                                    MotionEvent.ACTION_UP -> {
                                        //Clicked
                                        onClick(
                                            scope = scope,
                                            pagerState = pagerState,
                                            index = index
                                        )
                                        backgroundColor.value = Color.Transparent
                                        clickedInd.value = -1
                                    }
                                    MotionEvent.ACTION_CANCEL -> {
                                        backgroundColor.value = Color.Transparent
                                        clickedInd.value = -1
                                    }
                                }
                                true
                            }
                            .padding(
                                spacing6
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
//                        AnimatedVisibility(color != null && color != Color.Transparent && pagerState.currentPage == index) {
//                            Row {
//                                Spacer(
//                                    modifier = Modifier
//                                        .size(8.dp)
//                                        .clip(CircleShape)
//                                        .background(color = color ?: Color.Transparent)
//                                )
//                                Spacer(modifier = Modifier.width(spacing8))
//                            }
//                        }
                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                text = list[index],
                                color = if (pagerState.currentPage == index) Base900 else Base700,
                                fontSize = fontSize14,
                                fontWeight = FontWeight.Medium,
                                style = medium
                            )
                        }

                    }
                },
                selected = clickedInd.value == index,
                selectedContentColor = Base50,
                onClick = {
                    onClick(scope = scope, pagerState = pagerState, index = index)
                }
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabLayoutWithoutPager(
    modifier: Modifier = Modifier,
    unchosenColor: Color = Base900,
    chosenTextColor: Color = Base900,
    currentPage: Int,
    onTabClicked: (Int) -> Unit,
    titles: List<String>
) {
    TabRow(
        selectedTabIndex = currentPage,
        modifier = modifier
            .background(
                color = Base200,
                shape = RoundedCornerShape(cornerRadius12)
            )
            .padding(spacing4),
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
                    .tabIndicatorOffset(tabPositions[currentPage])
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(cornerRadius10))
                    .zIndex(1f),
                color = Base50
            )
        }
    ) {
        titles.forEachIndexed { index, _ ->
            Tab(
                modifier = Modifier
                    .clip(RoundedCornerShape(cornerRadius8))
                    .zIndex(2f),
                content = {
                    Box(
                        modifier = Modifier
                            .padding(vertical = spacing8)
                    ) {
                        Text(
                            titles[index],
                            color = if (currentPage == index) chosenTextColor else unchosenColor,
                            fontSize = fontSize14,
                            fontWeight = FontWeight.Medium,
                            style = medium
                        )
                    }
                },
                selected = currentPage == index,
                selectedContentColor = Base50,
                onClick = {
                    onTabClicked(index)
                }
            )
        }
    }
}
