package com.example.livescoresdu.data.response

data class PointsResponse(
    val groupName: String? = null,
    val teamName: String? = null,
    val gamePlayed: Int,
    val winCount: Int,
    val drawCount: Int,
    val loseCount: Int,
    val goalCount: Int,
    val goalMissed: Int,
    val points: Int
)