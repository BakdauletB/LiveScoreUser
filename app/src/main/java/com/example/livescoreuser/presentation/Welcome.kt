package com.example.livescoreuser.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livescoresdu.uilibrary.values.CustomButton
import com.example.livescoresdu.uilibrary.values.CustomButtonText
import com.example.livescoresdu.uilibrary.values.SharedPreferencesHelper
import com.example.livescoresdu.uilibrary.values.click
import com.example.livescoreuser.R
import com.example.livescoreuser.presentation.viewmodels.WelcomViewModel
import com.example.livescoreuser.values.ItemList
import com.example.livescoreuser.values.OnBoardingData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import ffinbank.myfreedom.uilibrary.values.Base50
import ffinbank.myfreedom.uilibrary.values.Base900
import ffinbank.myfreedom.uilibrary.values.Orange500
import ffinbank.myfreedom.uilibrary.values.fontSize16
import ffinbank.myfreedom.uilibrary.values.fontSize28
import ffinbank.myfreedom.uilibrary.values.semiBold
import ffinbank.myfreedom.uilibrary.values.spacing100
import ffinbank.myfreedom.uilibrary.values.spacing16
import ffinbank.myfreedom.uilibrary.values.spacing32
import ffinbank.myfreedom.uilibrary.values.spacing64
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Welcome(
    onClick: () -> Unit,
    viewModel: WelcomViewModel = getViewModel()
){

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = scope,){
        viewModel.getTournamentList()
    }

    Surface(modifier = Modifier.fillMaxSize()) {

        val items = ArrayList<OnBoardingData>()

        items.add(
            OnBoardingData(
                R.drawable.photi,
                "LIVE SCORE",
                welcome = "WELCOME",
                "Never miss a goal - Get live match alerts, fixtures and results for your favourite team and competitions",
            )
        )

        items.add(
            OnBoardingData(
                0,
                "Follow Tournaments",
                welcome =  "",
                "",

                )
        )

        items.add(
            OnBoardingData(
                R.drawable.background,
                "Live Score",
                "",
                desc = ""
            )
        )


        val pagerState = rememberPagerState(
            pageCount = items.size,
        )


        OnBoardingPager(
            item = items,
            pagerState = pagerState, modifier = Modifier
                .fillMaxWidth()
                .background(color = Base900),
            onClick = onClick
        )

    }



}
@ExperimentalPagerApi
@Composable
fun OnBoardingPager(
    item: List<OnBoardingData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    viewModel: WelcomViewModel = getViewModel()
) {
    val tournaments = viewModel.tournament
    val isNextVisible = remember { derivedStateOf { pagerState.currentPage != item.size - 1 } }
    val isPrevVisible = remember { derivedStateOf { pagerState.currentPage != 0 } }
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier.background(Base900),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.75f)
        ) {
            HorizontalPager(state = pagerState,
                verticalAlignment = Alignment.CenterVertically,
              ) { page ->
                when(pagerState.currentPage){
                    0 -> {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Base900),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = item[pagerState.currentPage].image),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(300.dp)
                            )
                            Spacer(modifier = Modifier.height(spacing16))
                            Text(text = item[pagerState.currentPage].title,
                                color = Base50,
                                fontSize = fontSize16,
                                fontWeight = FontWeight.SemiBold,
                                style = semiBold
                            )
                            Spacer(modifier = Modifier.height(spacing32))
                            Text(text = item[pagerState.currentPage].welcome,
                                color = Base50,
                                fontSize = fontSize16,
                                fontWeight = FontWeight.SemiBold,
                                style = semiBold)
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = spacing16, end = spacing16),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center) {
                                Text(text = item[pagerState.currentPage].desc,
                                    color = Base50,
                                    fontSize = fontSize16,
                                    fontWeight = FontWeight.SemiBold,
                                    style = semiBold,)
                            }


                        }
                    }
                    1 -> {
                        Column(modifier = Modifier
                            .background(color = Base900)
                            .padding(spacing16)) {
                            Text(text = item[pagerState.currentPage].title,
                                color = Base50,
                                fontSize = fontSize28,
                                fontWeight = FontWeight.SemiBold,
                                style = semiBold
                            )
                            Spacer(modifier = Modifier.height(spacing16))
                            tournaments.forEach { image ->
                                val isFavorite by viewModel.favsCache.collectAsState()
                                ItemList(image, isFavorite.containsKey(image.tournamentId),
                                    isFavCallback = { value ->
                                        if (value) {
                                            viewModel.addFavorite(image)
                                        } else {
                                            viewModel.removeFavorite(image)
                                        }
                                    }) {

                                }
                                Spacer(modifier = Modifier.height(spacing16))
                            }
                        }
                    }
                    2 -> {
//                        Column(modifier = Modifier
//                            .fillMaxSize()
//                            .background(color = Base900),
//                             horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Image(painter = painterResource(id = R.drawable.background),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize())
                            Text(text = item[pagerState.currentPage].title,
                                color = Base50,
                                fontSize = fontSize16,
                                fontWeight = FontWeight.SemiBold,
                                style = semiBold
                            )

                        }
                    //}
                }

            }

        }
        HorizontalPagerIndicator(
            pagerState = pagerState, modifier = Modifier
                .padding(vertical = 26.dp),
            activeColor = Orange500
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            if (isPrevVisible.value) {
                Column(modifier = Modifier.weight(1f)) {
                    CustomButton(buttonColors = ButtonDefaults.buttonColors(backgroundColor = Orange500),
                        modifier = Modifier
                            .click {
                                onClick()
                            }
                            .padding(start = spacing16, end = spacing16),
                        content = {
                            CustomButtonText(
                                text = "Prev",
                                color = Base900
                            )
                        }) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                }

            }
            if (isPrevVisible.value && isNextVisible.value) {
                Box(modifier = Modifier.fillMaxWidth(.2f))
            }

            if (isNextVisible.value) {
                Column(modifier = Modifier.weight(1f)) {
                    CustomButton(buttonColors = ButtonDefaults.buttonColors(backgroundColor = Orange500),
                        modifier = Modifier
                            .click {
                                onClick()
                            }
                            .padding(start = spacing16, end = spacing16),
                        content = {
                            CustomButtonText(
                                text = "Next",
                                color = Base900
                            )
                        }) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            }
            if(pagerState.currentPage == 2){
                Column(modifier = Modifier.weight(1f)) {
                    CustomButton(buttonColors = ButtonDefaults.buttonColors(backgroundColor = Orange500),
                        modifier = Modifier
                            .click {
                                onClick()
                            }
                            .padding(start = spacing16, end = spacing16),
                        content = {
                            CustomButtonText(
                                text = "Home Page",
                                color = Base900
                            )
                        }) {
                        SharedPreferencesHelper.saveWelcome(true)
                        onClick()
                    }
                }
            }

        }
    }
}



@Composable
fun PagerIndicator(currentPage: Int, items: List<OnBoardingData>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        repeat(items.size) {
            Indicator(isSelected = it == currentPage, color = Orange500)
        }
    }
}
@Composable
fun Indicator(isSelected: Boolean, color: Color) {
    val width = animateDpAsState(targetValue = if (isSelected) 40.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) color else Color.Gray.copy(alpha = 0.5f)
            )
    )
}