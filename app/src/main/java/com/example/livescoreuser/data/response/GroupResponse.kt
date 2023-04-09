package com.example.livescoresdu.data.response

data class GroupResponse(
    val groupId: Int,
    val tournamentName: String? = null,
    val groupName: String? = null,
    val playoff: Boolean
)
