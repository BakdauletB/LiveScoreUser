package com.example.livescoresdu.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescoresdu.data.MatchRepository
import com.example.livescoresdu.data.request.AuthRequest
import com.example.livescoresdu.data.request.GameRequest
import com.example.livescoresdu.data.response.AuthResponse
import com.example.livescoresdu.data.response.GroupResponse
import com.example.livescoresdu.data.response.PlayerResponse
import com.example.livescoresdu.uilibrary.values.SharedPreferencesHelper
import com.example.livescoresdu.uilibrary.values.Status
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection

class AuthViewModel(private val repository: MatchRepository): ViewModel() {

    val username: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = mutableStateOf("")
    val passwordVisible: MutableState<Boolean> = mutableStateOf(false)

    val token: MutableState<String> = mutableStateOf("")
    val uiState: MutableState<UiStatus> = mutableStateOf(UiStatus.WAITING)


    fun auth(username:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.auth(
                body = AuthRequest(password = password, username = username))
                .collect{
                    when (it.status) {
                        Status.SUCCESS -> {
                            val result = it.data as AuthResponse
                            Log.e("TAG", "auth: $result", )
                            token.value = result.token
                            SharedPreferencesHelper.saveAccessToken(result.token)
                        }
                        Status.ERROR -> {

                        }
                        Status.LOADING -> {

                        }
                    }
                }
        }
    }

}