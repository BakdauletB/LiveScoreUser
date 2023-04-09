package com.example.livescoresdu.data.response

data class EventIdResponse(
    val eventId: Int,
    val eventName: String,
    val playerName: String,
    val playerId: Int,
    val minute: Int,
    val teamId:Int,
    val teamName: String,
    val gameScore: String,
    val assist: Assist,
    val penalty: Boolean
)
