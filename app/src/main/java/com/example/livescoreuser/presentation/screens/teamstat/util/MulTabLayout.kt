import com.example.livescoresdu.uilibrary.values.onClick
import ffinbank.myfreedom.uilibrary.values.Base50
import ffinbank.myfreedom.uilibrary.values.Base900
import ffinbank.myfreedom.uilibrary.values.Orange500
import ffinbank.myfreedom.uilibrary.values.cornerRadius8
import ffinbank.myfreedom.uilibrary.values.fontSize14
import ffinbank.myfreedom.uilibrary.values.medium
import ffinbank.myfreedom.uilibrary.values.spacing6
import android.view.MotionEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
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

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalPagerApi
@Composable
fun MulTabLayout(
    pagerState: PagerState,
    list: List<String>,
    list1: List<String>,
    color: Color? = null,
    startPadding: Dp,
    endPadding: Dp){

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
            )
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
                                        backgroundColor.value = Orange500
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
//                        AnimatedVisibility (color != null && color != Color.Transparent && pagerState.currentPage == index) {
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
                                color = if (pagerState.currentPage == index) Orange500 else Base50,
                                fontSize = fontSize14,
                                fontWeight = FontWeight.Medium,
                                style = medium
                            )
                            Text(
                                text = list1[index],
                                color = if (pagerState.currentPage == index) Orange500 else Base50,
                                fontSize = fontSize14,
                                fontWeight = FontWeight.Medium,
                                style = medium
                            )
//                            repeat(rewardPeriods.size){ pos->
//                            Text(
//                                text = rewardPeriods[index].getTitle(),
//                                color = if (pagerState.currentPage == index) Orange500 else Base50,
//                                fontSize = fontSize14,
//                                fontWeight = FontWeight.Medium,
//                                style = medium
//                            )
//                            Text(
//                                text = rewardPeriods[index].getDateAsString(),
//                                color = if (pagerState.currentPage == index) Orange500 else Base50,
//                                fontSize = fontSize14,
//                                fontWeight = FontWeight.Medium,
//                                style = medium
//                            )

//                            }
                        }

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