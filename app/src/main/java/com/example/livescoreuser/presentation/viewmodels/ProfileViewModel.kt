package com.example.livescoresdu.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescoresdu.data.MatchRepository
import com.example.livescoresdu.data.response.TeamAndPlayersResponse
import com.example.livescoresdu.uilibrary.values.Status
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: MatchRepository): ViewModel() {
    val teamAndPlayers: SnapshotStateList<TeamAndPlayersResponse> = mutableStateListOf()
    var refreshing by mutableStateOf(false)
    var teamState by mutableStateOf(UiStatus.WAITING)



    fun getTeamAndPlayers(){
        if (teamAndPlayers.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getTeamAndPlayers().collect {
                    when (it.status) {
                        Status.LOADING -> {
                            teamState = UiStatus.LOADING

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<TeamAndPlayersResponse>
                            teamAndPlayers.clear()
                            teamAndPlayers.addAll(result)
                            teamState = UiStatus.SUCCESS
                        }
                        Status.ERROR -> {
                            teamState = UiStatus.ERROR
                        }
                    }
                }
            }
        }
    }
    fun refresh() {
        refreshing = true
        teamAndPlayers.clear()
        getTeamAndPlayers()
    }
    fun stopRefreshing(){
        refreshing = false
    }


}