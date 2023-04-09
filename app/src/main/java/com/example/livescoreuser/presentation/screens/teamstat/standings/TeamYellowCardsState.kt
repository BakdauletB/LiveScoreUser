package com.example.livescore.presentation.screens.standings

import com.example.livescore.data.remote.dto.TeamStatisticsGoalsResponse

data class TeamYellowCardsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val standings: List<TeamStatisticsGoalsResponse> = emptyList()
)
