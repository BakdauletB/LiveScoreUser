package com.example.livescoresdu.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescoresdu.data.MatchRepository
import com.example.livescoresdu.data.response.GameIdResponse
import com.example.livescoresdu.data.response.GameStartResponse
import com.example.livescoresdu.data.response.ProtocolResponse
import com.example.livescoresdu.uilibrary.values.Status
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchesDetailViewModel(private val repository: MatchRepository): ViewModel() {

    val protocol: MutableState<ProtocolResponse?> = mutableStateOf(null)
    val gameStart: MutableState<GameStartResponse?> = mutableStateOf(null)
    val status: MutableState<UiStatus> = mutableStateOf(UiStatus.WAITING)



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
    fun loadStartGame(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGameStart(id).collect {
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


}