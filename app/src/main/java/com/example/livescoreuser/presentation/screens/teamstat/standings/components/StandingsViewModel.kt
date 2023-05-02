package com.example.livescoreuser.presentation.screens.teamstat.standings.components

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescoresdu.data.MatchRepository
import com.example.livescoresdu.data.response.PlayerGoalsResponse
import com.example.livescoresdu.data.response.PointsResponse
import com.example.livescoresdu.data.response.TeamStatisticsGoalsResponse
import com.example.livescoresdu.uilibrary.values.Status
import com.example.livescoreuser.data.response.GroupPointsResponse
import com.example.livescoreuser.presentation.screens.bundle.TournamentBundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StandingsViewModel(private val repository: MatchRepository): ViewModel() {

    val goals: SnapshotStateList<TeamStatisticsGoalsResponse> = mutableStateListOf()
    val teamRedCards: SnapshotStateList<TeamStatisticsGoalsResponse> = mutableStateListOf()
    val teamYellowCards: SnapshotStateList<TeamStatisticsGoalsResponse> = mutableStateListOf()
    val points: SnapshotStateList<PointsResponse> = mutableStateListOf()
    val groupPoints: SnapshotStateList<GroupPointsResponse> = mutableStateListOf()
    val playerGoals: SnapshotStateList<PlayerGoalsResponse> = mutableStateListOf()
    val assists: SnapshotStateList<PlayerGoalsResponse> = mutableStateListOf()
    val redCards: SnapshotStateList<PlayerGoalsResponse> = mutableStateListOf()
    val yellowCards: SnapshotStateList<PlayerGoalsResponse> = mutableStateListOf()


    fun getGoals(){
        if (goals.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getGoals().collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<TeamStatisticsGoalsResponse>
                            goals.clear()
                            goals.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }
    fun getTeamRedCards(){
        if (teamRedCards.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getTeamRedCards().collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<TeamStatisticsGoalsResponse>
                            teamRedCards.clear()
                            teamRedCards.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }
    fun getTeamYellowCards(){
        if (teamYellowCards.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getTeamYellowCards().collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<TeamStatisticsGoalsResponse>
                            teamYellowCards.clear()
                            teamYellowCards.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }
//    fun getPoints(){
//        if (points.isEmpty()) {
//            viewModelScope.launch(Dispatchers.IO) {
//                repository.getPoints().collect {
//                    when (it.status) {
//                        Status.LOADING -> {
//
//                        }
//                        Status.SUCCESS -> {
//                            val result = it.data as List<PointsResponse>
//                            points.clear()
//                            points.addAll(result)
//                        }
//                        Status.ERROR -> {
//
//                        }
//                    }
//                }
//            }
//        }
//    }
    fun getGroupPoints(){
        if (groupPoints.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getGroupPoints(tournamentId = TournamentBundle.tournamentId,TournamentBundle.groupId).collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<GroupPointsResponse>
                            groupPoints.clear()
                            groupPoints.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }
    fun getPlayerGoals(){
        if (playerGoals.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getPlayerGoals().collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<PlayerGoalsResponse>
                            playerGoals.clear()
                            playerGoals.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }
    fun getAssists(){
        if (assists.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getAssists().collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<PlayerGoalsResponse>
                            assists.clear()
                            assists.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }
    fun getRedCards(){
        if (redCards.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getRedCards().collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<PlayerGoalsResponse>
                            redCards.clear()
                            redCards.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }
    fun getYellowCards(){
        if (yellowCards.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getYellowCards().collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<PlayerGoalsResponse>
                            yellowCards.clear()
                            yellowCards.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }


}