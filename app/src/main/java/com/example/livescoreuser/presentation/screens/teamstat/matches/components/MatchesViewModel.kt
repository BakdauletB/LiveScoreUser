package com.example.livescore.presentation.screens.matches.components

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescore.domain.use_cases.GameDateUseCase
import com.example.livescore.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val getGameDateUseCase: GameDateUseCase
) : ViewModel() {
//    private val _state = mutableStateOf(MatchesListState())
//    val state: State<MatchesListState> = _state


    private val _gameState = mutableStateOf(GameDateState())
    val gameState: State<GameDateState> = _gameState

    private val _gameState1 = mutableStateOf(GameDateState())
    val gameState1: State<GameDateState> = _gameState1

    private val _gameState2 = mutableStateOf(GameDateState())
    val gameState2: State<GameDateState> = _gameState2

    private val _gameState3 = mutableStateOf(GameDateState())
    val gameState3: State<GameDateState> = _gameState3

    private val _gameState4 = mutableStateOf(GameDateState())
    val gameState4: State<GameDateState> = _gameState4

    var matchId: String = ""
    var refreshing by mutableStateOf(false)

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh: StateFlow<Boolean> = _isRefresh

    val incomePeriods: SnapshotStateList<CalendarPeriodModel> = mutableStateListOf()
    val selectedPeriod: MutableState<CalendarPeriodModel?> = mutableStateOf(null)



    init {
        loadRewardPeriods()
        getGameDate()
        getGameDate1()
        getGameDate2()
        getGameDate3()
        getGameDate4()
    }
    fun refresh() {
        viewModelScope.launch {
            _isRefresh.emit(true)
            getGameDate()
            getGameDate1()
            getGameDate2()
            getGameDate3()
            getGameDate4()
            _isRefresh.emit(false)
        }
    }
    private fun getGameDate(){
        getGameDateUseCase("2023-04-03").onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _gameState.value = GameDateState(game = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _gameState.value = GameDateState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _gameState.value = GameDateState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getGameDate1(){
        getGameDateUseCase("2023-04-04").onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _gameState1.value = GameDateState(game = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _gameState1.value = GameDateState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _gameState1.value = GameDateState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getGameDate2(){
        getGameDateUseCase("2023-04-05").onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _gameState2.value = GameDateState(game = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _gameState2.value = GameDateState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _gameState2.value = GameDateState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getGameDate3(){
        getGameDateUseCase("2023-04-06").onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _gameState3.value = GameDateState(game = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _gameState3.value = GameDateState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _gameState3.value = GameDateState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getGameDate4(){
        getGameDateUseCase("2023-04-07").onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _gameState4.value = GameDateState(game = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _gameState4.value = GameDateState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _gameState4.value = GameDateState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
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
//    fun refresh() {
//        refreshing = true
//        getGameDate()
//        getGameDate1()
//        getGameDate2()
//        getGameDate3()
//        getGameDate4()
//    }
    fun stopRefreshing(){
        refreshing = false
    }


//    init {
//        getAllMatches()
//    }
//
//    fun getAllMatches() {
//        _list.value?.add(News(news_id = 1,
//                title_first = "Barabar",
//                title_second = "Sunkar",
//                img_first = R.drawable.barabar,
//                img_second = R.drawable.sunkar,
//                score_first = "1",
//                score_second = "0",
//                date = "FT"))
//        _list.value?.add(News(news_id = 1,
//            title_first = "Barabar",
//            title_second = "Sunkar",
//            img_first = R.drawable.barabar,
//            img_second = R.drawable.sunkar,
//            score_first = "1",
//            score_second = "0",
//            date = "FT"))
//        _list.value?.add(News(news_id = 1,
//            title_first = "Barabar",
//            title_second = "Sunkar",
//            img_first = R.drawable.barabar,
//            img_second = R.drawable.sunkar,
//            score_first = "1",
//            score_second = "0",
//            date = "FT"))
//        _list.value?.add(News(news_id = 1,
//            title_first = "Barabar",
//            title_second = "Sunkar",
//            img_first = R.drawable.barabar,
//            img_second = R.drawable.sunkar,
//            score_first = "1",
//            score_second = "0",
//            date = "FT"))
//        _list.value?.add(News(news_id = 1,
//            title_first = "Barabar",
//            title_second = "Sunkar",
//            img_first = R.drawable.barabar,
//            img_second = R.drawable.sunkar,
//            score_first = "1",
//            score_second = "0",
//            date = "FT"))






//        getMatchesUseCase().onEach { result ->
//            _state.value = MatchesListState(matches = )
////            when (result) {
////                is Resource.Success -> {
////                    _state.value = MatchesListState(matches = result.data ?: emptyList())
////                }
////                is Resource.Error -> {
////                    _state.value = MatchesListState(
////                        error = result.message ?: "An unexpected error occured"
////                    )
////                }
////                is Resource.Loading -> {
////                    _state.value = MatchesListState(isLoading = true)
////                }
////            }
//        }.launchIn(viewModelScope)
    //}
}
