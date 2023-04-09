package com.example.livescore.presentation.screens.matches.components

import com.example.livescore.data.remote.dto.GameDateResponse

data class GameDateState(
    val isLoading: Boolean = false,
    val error: String = "",
    val game: List<GameDateResponse> = emptyList()
)
