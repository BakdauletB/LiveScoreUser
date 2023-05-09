package com.example.livescoresdu.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescoresdu.data.MatchRepository
import com.example.livescoresdu.data.model.CalendarPeriodModel
import com.example.livescoresdu.data.model.Periods
import com.example.livescoresdu.data.request.GameRequest
import com.example.livescoresdu.data.response.*
import com.example.livescoresdu.uilibrary.values.SharedPreferencesHelper
import com.example.livescoresdu.uilibrary.values.Status
import com.example.livescoreuser.data.response.GetNewDateResponse
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.*


class MatchesViewModel(private val repository: MatchRepository
) : ViewModel() {


    val newDate: SnapshotStateList<GetNewDateResponse> = mutableStateListOf()
    val gameLive: SnapshotStateList<GetNewDateResponse> = mutableStateListOf()
    var newGameState by mutableStateOf(UiStatus.WAITING)
    var liveGameState by mutableStateOf(UiStatus.WAITING)
    var selectDate: MutableState<String> = mutableStateOf("")


    val documents: SnapshotStateList<File> = mutableStateListOf()
    private val documentUrls: MutableList<String> = mutableListOf()
    val documentsMaxSize = 3


    val hours: SnapshotStateList<Int> = mutableStateListOf()
    val minutes: SnapshotStateList<Int> = mutableStateListOf()
    val selectHour: MutableState<String> = mutableStateOf("")
    val selectMinutes: MutableState<String> = mutableStateOf("")

    init {
        hours.addAll(0..23)
        minutes.addAll(0..59)
    }
//    val selectDate: MutableState<String> = mutableStateOf("")
    val incomePeriods: SnapshotStateList<CalendarPeriodModel> = mutableStateListOf()
    val selectedPeriod: MutableState<CalendarPeriodModel?> = mutableStateOf(null)

    var cardExpanded by mutableStateOf(true)
    var refreshing by mutableStateOf(false)
    var gameState by mutableStateOf(UiStatus.WAITING)
    var gameState1 by mutableStateOf(UiStatus.WAITING)
    var gameState2 by mutableStateOf(UiStatus.WAITING)
    var gameState3 by mutableStateOf(UiStatus.WAITING)
    var gameState4 by mutableStateOf(UiStatus.WAITING)




    val team: SnapshotStateList<TeamResponse> = mutableStateListOf()
    val tournament: SnapshotStateList<TournamentUserResponse> = mutableStateListOf()
    val group: SnapshotStateList<GroupResponse> = mutableStateListOf()
    val selectedTournament: MutableState<Int> = mutableStateOf(0)
    val selectedGroup: MutableState<Int> = mutableStateOf(0)
    val selectedHome: MutableState<Int> = mutableStateOf(0)
    val selectedAway: MutableState<Int> = mutableStateOf(0)
    val selectedDate: MutableState<String> = mutableStateOf("")

    init {
        loadRewardPeriods()

    }


    private fun loadRewardPeriods() {
        incomePeriods.clear()
        incomePeriods.add(CalendarPeriodModel(type = Periods.THU))
        incomePeriods.add(CalendarPeriodModel(type = Periods.FRI))
        incomePeriods.add(CalendarPeriodModel(type = Periods.TODAY))
        incomePeriods.add(CalendarPeriodModel(type = Periods.SUN))
        incomePeriods.add(CalendarPeriodModel(type = Periods.MON))
    }
    fun setSelectedPeriod(dateFrom: Date,) {
        Log.d("dateFrom", dateFrom.toString())
//        incomePeriods.removeLast()
//        incomePeriods.add(RewardPeriodModel(Periods.SELECT_PERIOD))
        incomePeriods.last().setSelectedDate(date = dateFrom )
    }
    fun loadNewDate(date: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNewGameDate(date).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val result = it.data as List<GetNewDateResponse>
                        newDate.clear()
                        newDate.addAll(result)
                        newGameState = UiStatus.SUCCESS
                    }
                    Status.ERROR -> {
                        newGameState = UiStatus.ERROR
                    }
                    Status.LOADING -> {
                        newGameState = UiStatus.LOADING
                    }
                }
            }
        }
    }
    fun loadGameLive(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGameLive().collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val result = it.data as List<GetNewDateResponse>
                        gameLive.clear()
                        gameLive.addAll(result)
                        liveGameState = UiStatus.SUCCESS
                    }
                    Status.ERROR -> {
                        liveGameState = UiStatus.ERROR
                    }
                    Status.LOADING -> {
                        liveGameState = UiStatus.LOADING
                    }
                }
            }
        }
    }
    fun refresh() {
        refreshing = true
        newDate.clear()
        gameLive.clear()
        loadNewDate(selectDate.value)
        loadGameLive()


    }
    fun stopRefreshing(){
        refreshing = false
    }



}