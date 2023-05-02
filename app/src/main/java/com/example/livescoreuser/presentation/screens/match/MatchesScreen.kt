package com.example.livescoresdu.presentation.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.livescoresdu.data.model.InfoModel
import com.example.livescoresdu.data.response.GameDateResponse
import com.example.livescoresdu.presentation.screens.bundle.IdBundle
import com.example.livescoresdu.presentation.screens.bundle.PostBundle
import com.example.livescoresdu.presentation.screens.match.RequestStoragePermission
import com.example.livescoresdu.presentation.viewmodels.MatchesViewModel
import com.example.livescoresdu.presentation.viewmodels.swiftFileMimeTypes
import com.example.livescoresdu.uilibrary.values.*
import com.example.livescoreuser.R
import com.example.livescoreuser.data.response.GetNewDateResponse
import com.example.livescoreuser.presentation.screens.bundle.TournamentBundle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.mabn.calendarlibrary.ExpandableCalendar
import com.mabn.calendarlibrary.core.calendarDefaultTheme
import ffinbank.myfreedom.uilibrary.values.*
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.io.File
import java.time.LocalDate

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MatchesScreen(navController: NavController,
                  bottomNavHide: (Boolean) -> Unit,
                  viewModel: MatchesViewModel = getViewModel()){



//    val gameState = matchesViewModel.gameState.value
    val postGame = remember{
        mutableStateOf(false)
    }

    val selectTournament = viewModel.selectedTournament
    val selectGroup = viewModel.selectedGroup
    val selectHome = viewModel.selectedHome
    val selectAway = viewModel.selectedAway
    PostBundle.selectedTournament = selectTournament.value
    PostBundle.selectedGroup  = selectGroup.value
    PostBundle.selectedHome =  selectHome.value
    PostBundle.selectedAway = selectAway.value

    val homeTeam =  remember { mutableStateOf("") }
    val awayTeam = remember { mutableStateOf("") }
    val tournament = remember { mutableStateOf("") }
    val group = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val newDate = viewModel.newDate


    val alert = remember {
        mutableStateOf(false)
    }

    val currentInfo = remember {
        mutableStateOf(InfoModel())
    }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}
    var selectedText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(viewModel.incomePeriods.size)
    var openCalendarBottomSheet = remember {
        mutableStateOf(false)
    }
    val openTeamBottomSheet = remember {
        mutableStateOf(false)
    }
    val teamBottomSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        ) {
            openTeamBottomSheet.value = it != ModalBottomSheetValue.Hidden
            return@rememberModalBottomSheetState true
        }
    if(viewModel.gameState != UiStatus.LOADING &&
            viewModel.gameState1 != UiStatus.LOADING ||
            viewModel.gameState2 != UiStatus.LOADING ||
            viewModel.gameState3 != UiStatus.LOADING ||
            viewModel.gameState4 != UiStatus.LOADING){
        viewModel.stopRefreshing()
    }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewModel.refreshing,
        onRefresh = { scope.launch {
            viewModel.refresh()
        } })
    val currentDate = remember { mutableStateOf(LocalDate.now()) }
    viewModel.selectDate.value = currentDate.value.toString()
    LaunchedEffect(key1 = Unit) {
        if(viewModel.newDate.isEmpty()){
            viewModel.loadNewDate(currentDate.value.toString())
        }

    }

    val onDocumentClick = remember {
        mutableStateOf(false)
    }
    val documentPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { result ->
            try {
                val files: MutableList<File> = mutableListOf()
                result.forEach { uri ->
                    val file = File("").fromUri(context = context, uri = uri)
                    if (file.readBytes().size <= 10_000_000 && files.size < viewModel.documentsMaxSize) {
                        files.add(file)
                    }
                }
                files.forEach {
                    if (viewModel.documents.size < viewModel.documentsMaxSize) {
                        viewModel.documents.add(it)
                    } else return@forEach
                }
            } catch (e: Exception) {
                Log.e("TAG", "SwiftTransferScreen: $e")
            }
            onDocumentClick.value = false
        }
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(
                state = pullRefreshState,
                enabled = !viewModel.refreshing
            )
            .background(color = colorResource(id = R.color.black))
    ) {
        Scaffold(
            topBar = {
                Column() {
                    Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
                    Spacer(modifier = Modifier.height(spacing32))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = spacing6, end = spacing6),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(modifier = Modifier.weight(1f)) {
//                            viewModel.selectDate.value = currentDate.value.toString()
                            val scrollState = rememberScrollState()
                            Column(Modifier.verticalScroll(scrollState)) {
                                ExpandableCalendar(
                                    theme = calendarDefaultTheme.copy(
                                        dayShape = CircleShape,
                                        backgroundColor = Color.Black,
                                        selectedDayBackgroundColor = Color.White,
                                        dayValueTextColor = Color.White,
                                        selectedDayValueTextColor = Color.Black,
                                        headerTextColor = Color.White,
                                        weekDaysTextColor = Color.White
                                    ), onDayClick = {
                                        currentDate.value = it
                                    }, onLiveClick = {
//                                        live.value = true
//                                        viewModel.loadGameLive()

                                    }
                                )
                                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                    Text("Selected date: ${currentDate.value}")
                                }
                            }
                        }
                    }
                }
            }, backgroundColor = Base900,
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
//                    if(live.value){
//                        ListLiveGame(navController,viewModel.gameLive)
//                    } else {
                        ListGame(navController = navController, newDateResponse = newDate, alert,)
                    //}
                }
            }
            it.calculateBottomPadding()
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


    if(openCalendarBottomSheet.value){
        bottomNavHide(true)
    }

    CalendarBottomSheet(
        openCalendarBottomSheet = openCalendarBottomSheet.value,
        onOpenCalendarBottomSheet = {
            openCalendarBottomSheet.value = it
        },
        onBottomSheetHide = { date1,  ->
            if (date1 != null ) {
//                matchesViewModel.selectedPeriod.value = CalendarPeriodModel(Periods.SELECT_PERIOD)
                viewModel.setSelectedPeriod(date1,)
            }
            openCalendarBottomSheet.value = false
        }
    )
    if (openTeamBottomSheet.value) {
        LaunchedEffect(key1 = scope) {
            keyboardController?.hide()
            teamBottomSheetState.show()
        }
    } else {
        LaunchedEffect(key1 = scope) {
            teamBottomSheetState.hide()
        }
    }
    if (onDocumentClick.value) {
        RequestStoragePermission(
            onPermissionDenied = {},
            onPermissionGranted = {
                documentPickerLauncher.launch(swiftFileMimeTypes)
            }
        )
    }


}
@Composable
fun ListGame(navController: NavController,
//             gameDateResponse: SnapshotStateList<GameDateResponse>,
             newDateResponse: SnapshotStateList<GetNewDateResponse>,
             alert: MutableState<Boolean>,
             viewModel: MatchesViewModel = getViewModel()){
    val currentInfo = remember {
        mutableStateOf(InfoModel())
    }
    val context = LocalContext.current


    Column() {
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing10))
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing16))
        Column() {
            newDateResponse.forEach {
                LeagueItem(newDateResponse = it, onClick = {
                    TournamentBundle.tournamentId = it.tournamentId
                    TournamentBundle.groupId = it.groupId
                    navController.navigate(HomeDestinations.STANDING)
                })
            }
        }
        Spacer(modifier = Modifier.height(spacing16))
        LazyColumn(modifier = Modifier
            .background(color = Base900)){
                item {
                    newDateResponse.getOrNull(0)?.games?.forEachIndexed { index, newDateResponse ->
                        MatchItem(modifier = Modifier, games = newDateResponse, onClick = {
                            IdBundle.id = newDateResponse.protocolId
                            navController.navigate(HomeDestinations.MATCH_DETAIL_ADMIN + "/${newDateResponse.protocolId}")
                        })
                    }
                }
//            items(gameDateResponse.){ game ->
//                MatchItem(modifier = Modifier, gameDateResponse = game) {
////                    navController.navigate(Screen.MatchDetailsScreen.route + "/${game.gameId}")
//
//                }
//            }
        }
    }
//    if (onDocumentClick.value) {
//        RequestStoragePermission(
//            onPermissionDenied = {},
//            onPermissionGranted = {
//                documentPickerLauncher.launch(swiftFileMimeTypes)
//            }
//        )
//    }
}
@Composable
fun LeagueItem(
    newDateResponse: GetNewDateResponse,
    onClick: () -> Unit
){
    Row(modifier = Modifier.clickable {
                                      onClick()
    }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Column(modifier = Modifier
            .padding(start = spacing10)
            .background(color = Base50, RoundedCornerShape(cornerRadius12))
            .padding(spacing6),) {
            Image(painter = rememberAsyncImagePainter(newDateResponse.tournamentLogo),
                contentDescription = null,
                modifier = androidx.compose.ui.Modifier
                    .width(spacing32)
                    .height(spacing24))
        }
        Spacer(modifier = androidx.compose.ui.Modifier.width(spacing16))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = newDateResponse.tournamentName,
                color = Base50,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold)
            Spacer(modifier = androidx.compose.ui.Modifier.height(spacing6))
            Text(text = newDateResponse.groupName,
                color = Base700,
                fontSize = fontSize13,
                fontWeight = FontWeight.Medium,
                style = medium)

        }
    }
}
