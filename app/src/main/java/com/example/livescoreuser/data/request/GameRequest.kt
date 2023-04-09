package com.example.livescoresdu.data.request

import com.google.android.datatransport.runtime.dagger.multibindings.IntoMap

data class GameRequest(
    val dateTime: String,
    val groupId: Int,
    val team1Id: Int,
    val team2Id: Int,
)
