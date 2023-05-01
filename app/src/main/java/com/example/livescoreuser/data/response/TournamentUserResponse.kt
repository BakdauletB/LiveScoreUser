package com.example.livescoresdu.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tournament")
data class TournamentUserResponse(
    @PrimaryKey
    val tournamentId: Int,
    val tournamentName: String,
    val tournamentType: String
)
