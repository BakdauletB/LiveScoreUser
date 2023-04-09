package com.example.livescoresdu.data.response

data class PlayerResponse(
    val playerId:Int,
    val teamName: String? = null,
    val name: String? = null,
    val surname: String? = null,
    val playerNumber: Int,
    val role: String? = null
)
