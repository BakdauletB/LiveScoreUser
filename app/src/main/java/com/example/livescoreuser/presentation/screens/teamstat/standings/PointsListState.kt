package com.example.livescore.presentation.screens.standings

import com.example.livescoresdu.data.response.PointsResponse

data class PointsListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val points: List<PointsResponse> = emptyList()
)