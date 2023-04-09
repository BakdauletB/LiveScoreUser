package com.example.livescoresdu.data.response

import com.google.android.datatransport.runtime.dagger.multibindings.IntoMap

data class GameResponse(
    val gameId: Int,
    val groupId: Int,
    val team1Name: String? = null,
    val team2Name: String? = null,
    val team1Logo: String? = null,
    val team2Logo: String? = null,
    val gameScore: String? = null,
    val gameState: String? = null,
    val protocolId: Int,
    val gameDateTime: String? = null
)
