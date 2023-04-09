package com.example.livescoresdu.data.response

data class TeamAndPlayersResponse(
    val players: List<Players>,
    val teamId: Int,
    val teamLogo: String,
    val teamName: String
)
