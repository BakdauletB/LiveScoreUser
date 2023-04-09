package com.example.livescoresdu.data.response

data class PutEventResponse(
    val assist: Assist,
    val eventId: Int,
    val eventName:String,
    val gameScore: String,
    val minute: String,
    val penalty: Boolean,
    val playerId: Int,
    val playerName: String,
    val teamId: Int,
    val teamName: String,
)
