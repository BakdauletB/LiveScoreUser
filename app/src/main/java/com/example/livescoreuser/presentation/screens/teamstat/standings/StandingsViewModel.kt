package com.example.livescore.presentation.screens.standings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescore.domain.use_cases.*
import com.example.livescore.presentation.screens.scorers.ScorersDataState
import com.example.livescore.util.Resource
import com.example.livescoresdu.data.MatchRepository
import com.example.livescoresdu.data.response.GameDateResponse
import com.example.livescoresdu.data.response.PlayerGoalsResponse
import com.example.livescoresdu.data.response.PlayerResponse
import com.example.livescoresdu.uilibrary.values.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class StandingsViewModel(private val repository: MatchRepository) : ViewModel() {

    val assist: SnapshotStateList<PLayer> = mutableStateListOf()
    val redCard: SnapshotStateList<GameDateResponse> = mutableStateListOf()
    val yellowCard: SnapshotStateList<GameDateResponse> = mutableStateListOf()
    val playerGoals: SnapshotStateList<GameDateResponse> = mutableStateListOf()
    val points: SnapshotStateList<GameDateResponse> = mutableStateListOf()
    val scorers: SnapshotStateList<GameDateResponse> = mutableStateListOf()


    private val _isRefresh = MutableStateFlow(false)
    val isRefresh: StateFlow<Boolean> = _isRefresh



    init {
        getScorers()
        getPoints()
        getPlayersGoals()
        getAssists()
        getRedCards()
        getYellowCards()
        getTeamYellowCards()
        getTeamRedCards()
    }
    private fun getAssists() {
        if (assist.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getAssists().collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<PlayerGoalsResponse>
                            assist.clear()
                            assist.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }
    private fun getRedCards() {
        getRedCardsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _redcardState.value = RedCardState(points = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _redcardState.value = RedCardState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _redcardState.value = RedCardState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getYellowCards() {
        getYellowCardUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _yellowCardState.value = YellowCardState(points = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _yellowCardState.value = YellowCardState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _yellowCardState.value = YellowCardState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getPlayersGoals() {
        getPlayerGoalsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _playersState.value = PlayersGoalsState(points = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _playersState.value = PlayersGoalsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _playersState.value = PlayersGoalsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getPoints() {
        getPointsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _pointsState.value = PointsListState(points = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _pointsState.value = PointsListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _pointsState.value = PointsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getScorers() {
        getGoalsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = StandingsListState(standings = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = StandingsListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = StandingsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getTeamRedCards(){
        getTeamRedCardsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _teamRedCardsState.value = TeamRedCardsState(standings = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _teamRedCardsState.value = TeamRedCardsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _teamRedCardsState.value = TeamRedCardsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getTeamYellowCards(){
        getTeamYellowCardsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _teamYellowCardsState.value = TeamYellowCardsState(standings = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _teamYellowCardsState.value = TeamYellowCardsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _teamYellowCardsState.value = TeamYellowCardsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

//    init {
//        getStandings()
//    }
//
    fun refresh() {
        viewModelScope.launch {
            _isRefresh.emit(true)
            getScorers()
            _isRefresh.emit(false)
        }
    }

//    private fun getStandings() {
//        getStandingsUseCase().onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = StandingsListState(standings = result.data ?: emptyList())
//                }
//                is Resource.Error -> {
//                    _state.value = StandingsListState(
//                        error = result.message ?: "An unexpected error occured"
//                    )
//                }
//                is Resource.Loading -> {
//                    _state.value = StandingsListState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
}
