package com.example.livescoresdu.data.request

data class SaveGoalEventDtoRequest(
    val assistId:Int,
    val isPenalty: Boolean,
    val minute: Int,
    val playerId: Int,
    val protocolId: Int,
)
