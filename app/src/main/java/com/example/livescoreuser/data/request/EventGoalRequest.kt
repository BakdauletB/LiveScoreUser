package com.example.livescoresdu.data.request


data class EventGoalRequest(
    val assistId: String,
    val isPenalty: Boolean,
    val minute: Int,
    val playerId: Int,
    val protocolId: Int
)
