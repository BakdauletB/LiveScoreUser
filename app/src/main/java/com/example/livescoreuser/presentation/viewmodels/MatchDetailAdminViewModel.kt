package com.example.livescoreuser.presentation.viewmodels

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescoresdu.data.MatchRepository
import com.example.livescoresdu.data.request.EventGoalRequest
import com.example.livescoresdu.data.request.EventRequest
import com.example.livescoresdu.data.request.SaveEventRequest
import com.example.livescoresdu.data.request.SaveGoalEventDtoRequest
import com.example.livescoresdu.data.response.*
import com.example.livescoresdu.presentation.screens.bundle.IdBundle
import com.example.livescoresdu.uilibrary.values.Status
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchDetailAdminViewModel(private val repository: MatchRepository) : ViewModel(){

    val protocol: MutableState<ProtocolResponse?> = mutableStateOf(null)

    val eventId: MutableState<EventIdResponse?> = mutableStateOf(null)
    val player: SnapshotStateList<PlayerResponse> = mutableStateListOf()
    val playerTwo: SnapshotStateList<PlayerResponse> = mutableStateListOf()
    val playerUpdate: SnapshotStateList<PlayerResponse> = mutableStateListOf()
    var refreshing by mutableStateOf(false)


    val gameStart: MutableState<GameStartResponse?> = mutableStateOf(null)
    val status: MutableState<UiStatus> = mutableStateOf(UiStatus.WAITING)

    var team1: MutableState<Int?> = mutableStateOf(0)
    var team2: MutableState<Int?> = mutableStateOf(0)

    val selectedPlayer: MutableState<Int> = mutableStateOf(0)
    val selectedAssist: MutableState<Int> = mutableStateOf(0)
    val selectedMinute: MutableState<Int> = mutableStateOf(0)
    val selectPenalty: MutableState<Boolean> = mutableStateOf(false)
    val selectedPlayerYellow: MutableState<Int> = mutableStateOf(0)
    val selectedMinuteYellow: MutableState<Int> = mutableStateOf(0)
    val selectedPlayerRed: MutableState<Int> = mutableStateOf(0)
    val selectedMinuteRed: MutableState<Int> = mutableStateOf(0)


    val updatePlayer: MutableState<Int> = mutableStateOf(0)
    val updateSelectedAssist: MutableState<Int> = mutableStateOf(0)
    val updateSelectedMinute: MutableState<Int> = mutableStateOf(0)
    val updateSelectPenalty: MutableState<Boolean> = mutableStateOf(false)
    val updateSelectedPlayerYellow: MutableState<Int> = mutableStateOf(0)
    val updateSelectedMinuteYellow: MutableState<Int> = mutableStateOf(0)
    val updateSelectedPlayerRed: MutableState<Int> = mutableStateOf(0)
    val updateSelectedMinuteRed: MutableState<Int> = mutableStateOf(0)

    val selectedPlayerTeamTwo: MutableState<Int> = mutableStateOf(0)
    val selectedAssistTeamTwo: MutableState<Int> = mutableStateOf(0)
    val selectedMinuteTeamTwo: MutableState<Int> = mutableStateOf(0)
    val selectPenaltyTeamTwo: MutableState<Boolean> = mutableStateOf(false)
    val selectedPlayerYellowTeamTwo: MutableState<Int> = mutableStateOf(0)
    val selectedMinuteYellowTeamTwo: MutableState<Int> = mutableStateOf(0)
    val selectedPlayerRedTeamTwo: MutableState<Int> = mutableStateOf(0)
    val selectedMinuteRedTeamTwo: MutableState<Int> = mutableStateOf(0)

    var protocolState by mutableStateOf(UiStatus.WAITING)



    fun putEventUpd(id: Int,
                    eventEnumId: Int,
                       minute: Int,
                       playerId: Int,
                       protocolId: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.putEvent(id = id,
                body = SaveEventRequest(
                    eventEnumId = eventEnumId,
                    minute = minute,
                    playerId = playerId,
                    protocolId = protocolId
                )
            ).collect{
                when (it.status) {
                    Status.SUCCESS -> {

                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        }

    }
    fun putEventUpdate(id: Int,
                       assistId:Int,
                       isPenalty:Boolean,
                       minute: Int,
                       playerId: Int,
                       protocolId: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.putEventUpdate(id = id,
                        body = SaveGoalEventDtoRequest(
                            assistId= assistId,
                            isPenalty = isPenalty,
                            minute = minute,
                            playerId = playerId,
                            protocolId = protocolId
                        )
            ).collect{
                when (it.status) {
                    Status.SUCCESS -> {

                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        }

    }
    fun getEventId(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getEventId(id).collect {
                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        val result = it.data as EventIdResponse
                        eventId.value = result

                    }
                    Status.ERROR -> {

                    }
                }
            }
        }
    }

    fun postEvent(eventEnumId: Int,
                     minute: Int,
                     playerId:Int,
                     protocolId: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.postEvent(body = EventRequest(
                eventEnumId= eventEnumId,
                minute = minute,
                playerId = playerId,
                protocolId = protocolId)
            ).collect{
                when (it.status) {
                    Status.SUCCESS -> {

                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        }
    }
    fun postSaveGoalTwo(assistId: String,
                     isPenalty: Boolean,
                     minute: Int,
                     playerId:Int,
                     protocolId: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.postEventGoal(body = EventGoalRequest(
                assistId = assistId,
                isPenalty = isPenalty,
                minute = minute,
                playerId = playerId,
                protocolId = protocolId
            )
            ).collect{
                when (it.status) {
                    Status.SUCCESS -> {

                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        }
    }

    fun postSaveGoal(assistId: String,
                     isPenalty: Boolean,
                     minute: Int,
                     playerId:Int,
                     protocolId: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.postEventGoal(body = EventGoalRequest(
                assistId = assistId,
                isPenalty = isPenalty,
                minute = minute,
                playerId = playerId,
                protocolId = protocolId
            )
            ).collect{
                when (it.status) {
                    Status.SUCCESS -> {

                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        }
    }

    fun loadPlayers(teamId:Int){
        if (player.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getPlayer(teamId).collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<PlayerResponse>
                            player.clear()
                            player.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }
    fun loadPlayersTwo(teamId:Int){
        if (playerTwo.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getPlayer(teamId).collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<PlayerResponse>
                            playerTwo.clear()
                            playerTwo.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }

    fun updatePlayers(teamId:Int){
            viewModelScope.launch(Dispatchers.IO) {
                repository.getPlayer(teamId).collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<PlayerResponse>
                            playerUpdate.clear()
                            playerUpdate.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }

    }

    fun loadMatches(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProtocol(id).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val result = it.data as ProtocolResponse
                        protocol.value = result
                        protocolState = UiStatus.SUCCESS
                    }
                    Status.ERROR -> {
                        protocolState = UiStatus.ERROR
                    }
                    Status.LOADING -> {
                        protocolState = UiStatus.LOADING
                    }
                }
            }
        }
    }
    fun loadEndGame(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGameEnd(id).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val result = it.data as GameStartResponse
                        gameStart.value = result
                        status.value = UiStatus.SUCCESS
                    }
                    Status.ERROR -> {
                        status.value = UiStatus.ERROR
                    }
                    Status.LOADING -> {
                        status.value = UiStatus.LOADING
                    }
                }
            }
        }
    }
    fun refresh(){
        refreshing = true
        val protocolId = IdBundle.id
        loadMatches(protocolId)
    }
    fun stopRefreshing(){
        refreshing = false
    }

}