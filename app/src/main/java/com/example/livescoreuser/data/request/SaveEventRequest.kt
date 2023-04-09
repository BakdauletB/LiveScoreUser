package com.example.livescoresdu.data.request

data class SaveEventRequest(
    val eventEnumId:Int,
    val minute: Int,
    val playerId: Int,
    val protocolId: Int,
)
