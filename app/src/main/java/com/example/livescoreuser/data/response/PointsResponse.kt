package com.example.livescoresdu.data.response

data class PointsResponse(
    val teamName: String? = null,
    val teamLogo: String,
    val gamePlayed: Int,
    val winCount: Int,
    val drawCount: Int,
    val loseCount: Int,
    val goalCount: Int,
    val goalMissed: Int,
    val live: Boolean,
    val position: Int,
    val points: Int
)