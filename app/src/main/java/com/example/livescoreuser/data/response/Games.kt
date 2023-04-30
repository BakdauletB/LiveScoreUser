package com.example.livescoreuser.data.response

data class Games(
    val gameId: Int,
    val groupId: Int,
    val team1Name: String,
    val team2Name: String,
    val team1Logo: String,
    val team2Logo: String,
    val gameScore:String,
    val gameState:String,
    val protocolId: Int,
    val gameDateTime: String
)
