package com.example.livescoresdu.data.response

data class GameStartResponse(
    val gameDateTime: String? = null,
    val gameId: Int,
    val gameScore: String? = null,
    val gameState: String? = null,
    val groupId: Int,
    val protocolId: Int,
    val team1Logo: String,
    val team1Name: String,
    val team2Logo: String,
    val team2Name: String
)
