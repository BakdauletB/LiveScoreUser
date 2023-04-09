package com.example.livescoresdu.presentation.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.livescoresdu.data.model.InfoModel
import com.example.livescoresdu.data.response.GameDateResponse
import com.example.livescoresdu.presentation.screens.bundle.IdBundle
import com.example.livescoresdu.presentation.screens.bundle.PostBundle
import com.example.livescoresdu.presentation.screens.match.RequestStoragePermission
import com.example.livescoresdu.presentation.viewmodels.MatchesViewModel
import com.example.livescoresdu.presentation.viewmodels.swiftFileMimeTypes
import com.example.livescoresdu.uilibrary.values.*
import com.example.livescoreuser.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ffinbank.myfreedom.uilibrary.values.*
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.io.File

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

    val loadGame = viewModel.game
    val game1 = viewModel.game1
    val game2 = viewModel.game2
    val game3 = viewModel.game3
    val game4 = viewModel.game4
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
    LaunchedEffect(key1 = Unit) {
        if(viewModel.game.isEmpty()){
            viewModel.loadMatches()
        }
        if(viewModel.game1.isEmpty()){
            viewModel.loadMatches1()
        }
        if(viewModel.game2.isEmpty()){
            viewModel.loadMatches2()
        }
        if(viewModel.game3.isEmpty()){
            viewModel.loadMatches3()
        }
        if(viewModel.game4.isEmpty()){
            viewModel.loadMatches4()
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
                        Column(
                            modifier = Modifier
                                .weight(0.2f)
                                .background(
                                    color = Base200,
                                    shape = RoundedCornerShape(cornerRadius16)
                                )
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
                                rewardPeriods = viewModel.incomePeriods,
//                                selectedPeriod = viewModel.selectedPeriod,
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
                                    navController.navigate(HomeDestinations.CALENDAR)
//                                    openCalendarBottomSheet.value = true
                                })
                    }
                }
            }, backgroundColor = Base900,
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                HorizontalPager(
                    state = pagerState, modifier = Modifier.wrapContentHeight(),
                    verticalAlignment = Alignment.Top
                ) { page ->
                    when (page) {
                        0 -> ListGame(navController = navController, gameDateResponse = loadGame, alert,)
                        1 -> ListGame1(navController = navController, game1, alert,)
                        2 -> ListGame2(navController = navController, game2, alert)
                        3 -> ListGame3(navController = navController, game3, alert)
                        4 -> ListGame3(navController = navController, game4, alert)
                    }
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
             gameDateResponse: SnapshotStateList<GameDateResponse>,
             alert: MutableState<Boolean>,
             viewModel: MatchesViewModel = getViewModel()){
    val currentInfo = remember {
        mutableStateOf(InfoModel())
    }
    val context = LocalContext.current


    Column() {
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing10))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .background(color = Base400)
            .padding(spacing1))
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing16))
        Row(modifier = Modifier.clickable {
//            navController.navigate(Screen.StandingScreen.route)
        }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Column(modifier = Modifier
                .padding(start = spacing10)
                .background(color = Base50, RoundedCornerShape(cornerRadius12))
                .padding(spacing6),) {
                Image(painter = painterResource(id = R.drawable.sdu_logo),
                    contentDescription = null,
                    modifier = androidx.compose.ui.Modifier
                        .width(spacing32)
                        .height(spacing24))
            }
            Spacer(modifier = androidx.compose.ui.Modifier.width(spacing16))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "SDU Football league",
                    color = Base50,
                    fontSize = fontSize16,
                    fontWeight = FontWeight.SemiBold,
                    style = semiBold)
                Spacer(modifier = androidx.compose.ui.Modifier.height(spacing6))
                Text(text = "Almaty",
                    color = Base700,
                    fontSize = fontSize13,
                    fontWeight = FontWeight.Medium,
                    style = medium)

            }
        }
//        Documents(
//            documents = viewModel.documents,
//            onOpenBottomSheetClick = {
////                currentInfo.value =
////                    viewModel.documentInfo.parseToInfoModel(context = context)
////                openInfoBottomSheet.value = true
//            },
//            onAddDocumentClick = {
//                //TODO Open documents
//                onDocumentClick.value = true
//            },
//            maxSize = viewModel.documentsMaxSize,
//            onDeleteDocumentClick = {
//                viewModel.documents.remove(it)
//                it.delete()
//            }
//        )


        LazyColumn(modifier = Modifier
            .background(color = Base900)){
                item {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing16)
                            .clickable {
                                alert.value = true
                            }
                            .background(shape = RoundedCornerShape(cornerRadius12), color = Base800)
                            .clip(RoundedCornerShape(cornerRadius16))
                            .padding(spacing16),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null)
                    }
                }
                item {
                    gameDateResponse.forEachIndexed { index, gameDateResponse ->
                        MatchItem(modifier = Modifier, gameDateResponse = gameDateResponse, onClick = {
                            IdBundle.id = gameDateResponse.protocolId
                            navController.navigate(HomeDestinations.MATCH_DETAIL + "/${gameDateResponse.protocolId}")
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
fun ListGame1(navController: NavController,
              gameDateResponse: SnapshotStateList<GameDateResponse>,
              alert: MutableState<Boolean>){
    Column() {
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing10))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .background(color = Base400)
            .padding(spacing1))
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing16))
        Row(modifier = Modifier.clickable {
//            navController.navigate(Screen.StandingScreen.route)
        }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Column(modifier = Modifier
                .padding(start = spacing10)
                .background(color = Base50, RoundedCornerShape(cornerRadius12))
                .padding(spacing6),) {
                Image(painter = painterResource(id = R.drawable.sdu_logo),
                    contentDescription = null,
                    modifier = androidx.compose.ui.Modifier
                        .width(spacing32)
                        .height(spacing24))
            }
            Spacer(modifier = androidx.compose.ui.Modifier.width(spacing16))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "SDU Football league",
                    color = Base50,
                    fontSize = fontSize16,
                    fontWeight = FontWeight.SemiBold,
                    style = semiBold)
                Spacer(modifier = androidx.compose.ui.Modifier.height(spacing6))
                Text(text = "Almaty",
                    color = Base700,
                    fontSize = fontSize13,
                    fontWeight = FontWeight.Medium,
                    style = medium)

            }
        }


        LazyColumn(modifier = Modifier
            .background(color = Base900)){
            item {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing16)
                        .clickable {
                            alert.value = true
                        }
                        .background(shape = RoundedCornerShape(cornerRadius12), color = Base800)
                        .clip(RoundedCornerShape(cornerRadius16))
                        .padding(spacing16),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null)
                }
            }
            item {
                gameDateResponse.forEachIndexed { index, gameDateResponse ->
                    MatchItem(modifier = Modifier, gameDateResponse = gameDateResponse, onClick = {
                        IdBundle.id = gameDateResponse.protocolId
                        navController.navigate(HomeDestinations.MATCH_DETAIL + "/${gameDateResponse.protocolId}")
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

}
@Composable
fun ListGame2(navController: NavController,
             gameDateResponse: SnapshotStateList<GameDateResponse>,
             alert: MutableState<Boolean>){
    Column() {
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing10))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .background(color = Base400)
            .padding(spacing1))
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing16))
        Row(modifier = Modifier.clickable {
//            navController.navigate(Screen.StandingScreen.route)
        }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Column(modifier = Modifier
                .padding(start = spacing10)
                .background(color = Base50, RoundedCornerShape(cornerRadius12))
                .padding(spacing6),) {
                Image(painter = painterResource(id = R.drawable.sdu_logo),
                    contentDescription = null,
                    modifier = androidx.compose.ui.Modifier
                        .width(spacing32)
                        .height(spacing24))
            }
            Spacer(modifier = androidx.compose.ui.Modifier.width(spacing16))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "SDU Football league",
                    color = Base50,
                    fontSize = fontSize16,
                    fontWeight = FontWeight.SemiBold,
                    style = semiBold)
                Spacer(modifier = androidx.compose.ui.Modifier.height(spacing6))
                Text(text = "Almaty",
                    color = Base700,
                    fontSize = fontSize13,
                    fontWeight = FontWeight.Medium,
                    style = medium)

            }
        }


        LazyColumn(modifier = Modifier
            .background(color = Base900)){
            item {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing16)
                        .clickable {
                            alert.value = true
                        }
                        .background(shape = RoundedCornerShape(cornerRadius12), color = Base800)
                        .clip(RoundedCornerShape(cornerRadius16))
                        .padding(spacing16),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null)
                }
            }
            item {
                gameDateResponse.forEachIndexed { index, gameDateResponse ->
                    MatchItem(modifier = Modifier, gameDateResponse = gameDateResponse, onClick = {
                        IdBundle.id = gameDateResponse.protocolId
                        navController.navigate(HomeDestinations.MATCH_DETAIL + "/${gameDateResponse.protocolId}")
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

}
@Composable
fun ListGame3(navController: NavController,
              gameDateResponse: SnapshotStateList<GameDateResponse>,
              alert: MutableState<Boolean>){
    Column() {
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing10))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .background(color = Base400)
            .padding(spacing1))
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing16))
        Row(modifier = Modifier.clickable {
//            navController.navigate(Screen.StandingScreen.route)
        }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Column(modifier = Modifier
                .padding(start = spacing10)
                .background(color = Base50, RoundedCornerShape(cornerRadius12))
                .padding(spacing6),) {
                Image(painter = painterResource(id = R.drawable.sdu_logo),
                    contentDescription = null,
                    modifier = androidx.compose.ui.Modifier
                        .width(spacing32)
                        .height(spacing24))
            }
            Spacer(modifier = androidx.compose.ui.Modifier.width(spacing16))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "SDU Football league",
                    color = Base50,
                    fontSize = fontSize16,
                    fontWeight = FontWeight.SemiBold,
                    style = semiBold)
                Spacer(modifier = androidx.compose.ui.Modifier.height(spacing6))
                Text(text = "Almaty",
                    color = Base700,
                    fontSize = fontSize13,
                    fontWeight = FontWeight.Medium,
                    style = medium)

            }
        }


        LazyColumn(modifier = Modifier
            .background(color = Base900)){
            item {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing16)
                        .clickable {
                            alert.value = true
                        }
                        .background(shape = RoundedCornerShape(cornerRadius12), color = Base800)
                        .clip(RoundedCornerShape(cornerRadius16))
                        .padding(spacing16),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null)
                }
            }
            item {
                gameDateResponse.forEachIndexed { index, gameDateResponse ->
                    MatchItem(modifier = Modifier, gameDateResponse = gameDateResponse, onClick = {
                        IdBundle.id = gameDateResponse.protocolId
                        navController.navigate(HomeDestinations.MATCH_DETAIL + "/${gameDateResponse.protocolId}")
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

}