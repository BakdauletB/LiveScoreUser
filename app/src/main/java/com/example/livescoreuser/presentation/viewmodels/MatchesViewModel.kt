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
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.*


class MatchesViewModel(private val repository: MatchRepository
) : ViewModel() {


    val game: SnapshotStateList<GameDateResponse> = mutableStateListOf()
    val game1: SnapshotStateList<GameDateResponse> = mutableStateListOf()
    val game2: SnapshotStateList<GameDateResponse> = mutableStateListOf()
    val game3: SnapshotStateList<GameDateResponse> = mutableStateListOf()
    val game4: SnapshotStateList<GameDateResponse> = mutableStateListOf()


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
    fun loadMatches(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGameDate("2023-04-03").collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val result = it.data as List<GameDateResponse>
                        game.clear()
                        game.addAll(result)
                        gameState = UiStatus.SUCCESS
                    }
                    Status.ERROR -> {
                        gameState = UiStatus.ERROR
                    }
                    Status.LOADING -> {
                        gameState = UiStatus.LOADING
                    }
                }
            }
        }
    }
    fun loadMatches1(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGameDate("2023-04-04").collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val result = it.data as List<GameDateResponse>
                        game1.clear()
                        game1.addAll(result)
                        gameState1 = UiStatus.SUCCESS
                    }
                    Status.ERROR -> {
                        gameState1 = UiStatus.ERROR
                    }
                    Status.LOADING -> {
                        gameState1 = UiStatus.LOADING
                    }
                }
            }
        }
    }
    fun loadMatches2(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGameDate("2023-04-05").collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val result = it.data as List<GameDateResponse>
                        game2.clear()
                        game2.addAll(result)
                        gameState2 = UiStatus.SUCCESS
                    }
                    Status.ERROR -> {
                        gameState2 = UiStatus.ERROR
                    }
                    Status.LOADING -> {
                        gameState2 = UiStatus.LOADING
                    }
                }
            }
        }
    }
    fun loadMatches3(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGameDate("2023-04-06").collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val result = it.data as List<GameDateResponse>
                        game3.clear()
                        game3.addAll(result)
                        gameState2 = UiStatus.SUCCESS
                    }
                    Status.ERROR -> {
                        gameState2 = UiStatus.ERROR
                    }
                    Status.LOADING -> {
                        gameState2 = UiStatus.LOADING
                    }
                }
            }
        }
    }
    fun loadMatches4(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGameDate("2023-04-07").collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val result = it.data as List<GameDateResponse>
                        game4.clear()
                        game4.addAll(result)
                        gameState4 = UiStatus.SUCCESS
                    }
                    Status.ERROR -> {
                        gameState2 = UiStatus.ERROR
                    }
                    Status.LOADING -> {
                        gameState2 = UiStatus.LOADING
                    }
                }
            }
        }
    }
    fun refresh() {
        refreshing = true
        team.clear()
        game.clear()
            game1.clear()
        game2.clear()
        team.clear()
        tournament.clear()
        group.clear()
        loadMatches()
        loadMatches1()
        loadMatches2()
        loadMatches3()
        loadMatches4()
    }
    fun stopRefreshing(){
        refreshing = false
    }



}