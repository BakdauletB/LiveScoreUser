package com.example.livescore.presentation.screens.standings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.livescore.presentation.screens.playerstat.*
import com.example.livescore.presentation.screens.scorers.components.GoalListItem
import com.example.livescore.presentation.screens.scorers.components.PlayerGoalsItem
import com.example.livescore.presentation.screens.scorers.components.PointsListItem
import com.example.livescore.presentation.screens.standings.*
import com.example.livescore.presentation.screens.teamstat.TeamGoals
import com.example.livescore.presentation.screens.teamstat.TeamRedCards
import com.example.livescore.presentation.screens.teamstat.TeamYellowCards
import com.example.livescore.util.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ffinbank.myfreedom.uilibrary.values.*

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun PlayerStats(players: PlayersGoalsState,assistsGoalState: AssistsGoalState,redCardState: RedCardState,yellowCardState: YellowCardState){
    val pagerState = rememberPagerState(5)

    Column(modifier = Modifier.padding(bottom = spacing36)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = spacing16, end = spacing16),
                verticalAlignment = Alignment.CenterVertically) {
                TabLayout(
                    pagerState = pagerState,
                    list = listOf("ALL","GOALS","ASSISTS","RED CARDS", "YELLOW CARDS",),
                    startPadding = spacing16,
                    endPadding = spacing16
                )
            }
            HorizontalPager(state = pagerState) { page ->
                when(page){
                    0 -> All(
                        players = players,
                        assistsGoalState = assistsGoalState,
                        redCardState = redCardState,
                        yellowCardState = yellowCardState
                    )
                    1 -> Goals(players = players)
                    2 -> Assists(assistsGoalState = assistsGoalState)
                    3 -> RedCards(redCardState = redCardState)
                    4 -> YellowCards(yellowCardState = yellowCardState)
                }
            }
        }
}

@Composable
fun Table(points: PointsListState){
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                spacing16
            )
        ) {
            Text(
                text = "#",
                color = Base50,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
            )
            Spacer(modifier = Modifier.width(spacing6))
            Text(
                text = "Team",
                color = Base50,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "P",
                color = Base50,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
            )
            Spacer(modifier = Modifier.width(spacing6))
            Text(
                text = "GD",
                color = Base50,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
            )
            Spacer(modifier = Modifier.width(spacing6))
            Text(
                text = "PTS",
                color = Base50,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            color = Base700
        )

//        items(points.points){ standings ->
//            PointsListItem(pointsResponse = standings)
//            }
//        }
        points.points.forEachIndexed { index,point ->
            PointsListItem(pointsResponse = point, count = index+1)
        }
    }

}
@Composable
fun Overview(){
Column() {
    
}
}
@Composable
fun Matches(){
Column() {
    
}
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TeamStats(matchesGoals: StandingsListState,teamYellowCardsState: TeamYellowCardsState,teamRedCardsState: TeamRedCardsState){
    val pagerState = rememberPagerState(3)

    Column(modifier = Modifier.padding(spacing16)) {

        Box(
            Modifier
                .fillMaxWidth()
                .padding(start = spacing20, end = spacing20), ) {
            TabLayout(
                pagerState = pagerState,
                list = listOf("GOALS","RED CARDS","YELLOW CARDS",),
                startPadding = spacing16,
                endPadding = spacing16
            )
        }
        Spacer(modifier = Modifier.height(spacing16))
        HorizontalPager(state = pagerState) { page ->
            when(page){
                0 -> TeamGoals(matchesGoals = matchesGoals)
                1 -> TeamRedCards(teamRedCardsState)
                2 -> TeamYellowCards(teamYellowCardsState = teamYellowCardsState)
            }
        }
       
    }
}