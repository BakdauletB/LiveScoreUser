package com.example.livescoresdu.data.response

data class Events(
    val eventId: Int,
    val eventName: String,
    val gameScore: String,
    val minute: Int,
    val playerName: String,
    val teamId: Int,
    val teamName: String,
    val assist: Assist?
)
