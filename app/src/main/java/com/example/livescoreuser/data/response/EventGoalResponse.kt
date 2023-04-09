package com.example.livescoresdu.data.response

data class EventGoalResponse(
    val eventId: Int,
    val eventName: String? = null,
    val playerName: String? = null,
    val playerId: Int,
    val minute: Int,
    val teamId: Int,
    val teamName: String? = null,
    val gameScore: String? = null,
    val assist: Assist,
    val penalty: Boolean
)
