package com.example.livescoreuser.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.livescore.util.Resource
import com.example.livescoresdu.data.MatchRepository
import com.example.livescoresdu.data.response.GameStartResponse
import com.example.livescoresdu.data.response.ProtocolResponse
import com.example.livescoresdu.uilibrary.values.Status
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScorersViewModel(private val repository: MatchRepository): ViewModel() {

//    private val _state = mutableStateOf(ScorersDataState())
//    val state: State<ScorersDataState> = _state
    val protocol: MutableState<ProtocolResponse?> = mutableStateOf(null)
    val status: MutableState<UiStatus> = mutableStateOf(UiStatus.WAITING)

    // to call get match details



    fun loadMatches(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProtocol(id).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val result = it.data as ProtocolResponse
                        protocol.value = result
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
}

//    init {
//       // getScorers()
//    }

//    private fun getScorers() {
//        getGoalsUseCase().onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = ScorersDataState(scorer = result.data ?: emptyList())
//                }
//                is Resource.Error -> {
//                    _state.value = ScorersDataState(
//                        error = result.message ?: "An unexpected error occured"
//                    )
//                }
//                is Resource.Loading -> {
//                    _state.value = ScorersDataState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
//}
