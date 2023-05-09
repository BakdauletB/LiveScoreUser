package com.example.livescoreuser.data.response

data class TournamentSearchResponse(
    val tournamentId: Int,
    val tournamentName: String,
    val tournamentType: String,
    val tournamentLogo: String,
    val tournamentLocation: String
)
