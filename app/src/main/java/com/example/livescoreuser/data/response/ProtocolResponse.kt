package com.example.livescoresdu.data.response

data class ProtocolResponse(
    val dateAndTime: String? = null,
    val protocolId: Int,
    val team1Logo: String,
    val team2Logo: String,
    val gameId: Int,
    val team1: String,
    val team2: String,
    val team1Id: Int,
    val team2Id: Int,
    val gameScore: String,
    val events: List<Events>,
    val gameState: String
)
