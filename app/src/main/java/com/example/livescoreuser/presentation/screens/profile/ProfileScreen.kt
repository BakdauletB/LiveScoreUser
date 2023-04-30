package com.example.livescoresdu.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.livescoresdu.data.response.Players
import com.example.livescoresdu.data.response.TeamAndPlayersResponse
import com.example.livescoresdu.presentation.viewmodels.ProfileViewModel
import ffinbank.myfreedom.uilibrary.values.*
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = getViewModel()
){
    LaunchedEffect(key1 = Unit,){
        viewModel.getTeamAndPlayers()
    }
    val scope = rememberCoroutineScope()
    if(viewModel.teamState == UiStatus.SUCCESS){
        viewModel.stopRefreshing()
    }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewModel.refreshing,
        onRefresh = { scope.launch {
            viewModel.refresh()
        } })
    val team = viewModel.teamAndPlayers
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(
            state = pullRefreshState,
            enabled = !viewModel.refreshing
        )
            .background(color = colorResource(id = com.example.livescoreuser.R.color.black))
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
                    painter = painterResource( com.example.livescoreuser.R.drawable.ic_back_arrow),
                    contentDescription = null,
                    tint = Base700,)
            }
            LazyColumn(){
                item {
                    team.forEach {
                        TeamAndPlayerItem(it)
                    }
                }
            }
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

}
@Composable
fun TeamAndPlayerItem(teamAndPlayersResponse: TeamAndPlayersResponse){
    Column(modifier = Modifier.padding(top = spacing16, bottom = spacing16)) {
        Row(modifier = Modifier.fillMaxWidth()
            .padding(start = spacing16, end = spacing16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = teamAndPlayersResponse.teamId.toString(),
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
                color =  Base50)
            Image(painter = rememberAsyncImagePainter(teamAndPlayersResponse.teamLogo),
                contentDescription = null,
                modifier = Modifier.size(spacing32))
            Text(text =  teamAndPlayersResponse.teamName,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
                color =  Base50)
        }
        Spacer(modifier = Modifier.height(spacing16))
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
        ){
            teamAndPlayersResponse.players.forEachIndexed {index,goals ->
                PlayersItem(teamAndPlayersResponse = goals,)
            }
        }
    }
}
@Composable
fun PlayersItem(teamAndPlayersResponse: Players,){
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(spacing16)
        ) {
            Text(text = "${teamAndPlayersResponse.name} ${teamAndPlayersResponse.surname}",
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold,
                color =  Base50
            )
            Spacer(modifier = Modifier.width(spacing6))
            Text(
                text = "${teamAndPlayersResponse.playerNumber}",
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
    }
}