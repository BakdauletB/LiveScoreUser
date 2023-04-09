package com.example.livescore.presentation.screens.standings


import com.example.livescoresdu.data.response.PlayerGoalsResponse

data class PlayersGoalsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val points: List<PlayerGoalsResponse> = emptyList()
)