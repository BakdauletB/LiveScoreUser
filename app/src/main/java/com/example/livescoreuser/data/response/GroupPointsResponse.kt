package com.example.livescoreuser.data.response

import com.example.livescoresdu.data.response.PointsResponse

data class GroupPointsResponse(
    val groupId: Int,
    val groupName: String,
    val tournamentLogo: String,
    val tournamentName: String,
    val sortedByPointTeams: List<PointsResponse>
)
