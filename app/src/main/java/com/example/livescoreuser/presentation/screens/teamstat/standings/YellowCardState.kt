package com.example.livescore.presentation.screens.standings

import com.example.livescore.data.remote.dto.PlayerGoalsResponse


data class YellowCardState(
    val isLoading: Boolean = false,
    val error: String = "",
    val points: List<PlayerGoalsResponse> = emptyList()
)