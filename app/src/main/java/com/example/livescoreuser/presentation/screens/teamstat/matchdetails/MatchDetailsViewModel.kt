package com.example.livescore.presentation.screens.matchdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescore.domain.use_cases.ProtocolUseCase
import com.example.livescore.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(
    private val getProtocolUseCase: ProtocolUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(MatchDetailsState())
    val state: State<MatchDetailsState> = _state

    // to call get match details

    init {
        getProtocol()
    }
    private fun getProtocol(){
        getProtocolUseCase(1).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = MatchDetailsState(matchdetails = result.data ?: null)
                }
                is Resource.Error -> {
                    _state.value = MatchDetailsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MatchDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
















//    private val matchMessageList = listOf(
//        SummaryPush(
//            time = "5",
//            name = "Messi",
//            image = SummaryAction.Yellow(R.drawable.yellowcard),
//        ),
//        SummaryPush(
//            time = "45+2",
//            name = "Messi",
//            image = SummaryAction.Yellow(R.drawable.yellowcard),
//        ),
//        SummaryPush(
//            time = "45+3",
//            name = "Messi",
//            image = SummaryAction.Yellow(R.drawable.yellowcard),
//        ),
//        SummaryPush(
//            time = "78",
//            name = "Messi",
//            scoreOne = "0",
//            scoreSecond = "1",
//            image = SummaryAction.Yellow(R.drawable.ball),
//        ),
//        SummaryPush(
//            time = "84",
//            name = "Messi",
//            image = SummaryAction.Yellow(R.drawable.yellowcard),
//        ),
//        SummaryPush(
//            time = "89",
//            name = "Messi",
//            scoreOne = "0",
//            scoreSecond = "2",
//            image = SummaryAction.Yellow(R.drawable.ball),
//        ),
//
//    )
//
//    var listState by mutableStateOf(matchMessageList)
//
















//    init {
//        savedStateHandle.get<String>(Constants.PARAM_MATCH_ID)?.let { match ->
//            getMatchDetails(match)
//        }
//    }

//    private fun getMatchDetails(id: String) {
//        getMatchDetailsUseCase(id).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = MatchDetailsState(matchdetails = result.data)
////
//                }
//                is Resource.Error -> {
//                    _state.value = MatchDetailsState(
//                        error = result.message ?: "An unexpected error occured"
//                    )
//                }
//                is Resource.Loading -> {
//                    _state.value = MatchDetailsState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }

//    private fun getOdds(id: String) {
//        oddsUseCase(id).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = MatchDetailsState(odds = result.data)
//                }
//                is Resource.Error -> {
//                    _state.value = MatchDetailsState(
//                        error = result.message ?: "An unexpected error occured"
//                    )
//                }
//                is Resource.Loading -> {
//                    _state.value = MatchDetailsState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
}
