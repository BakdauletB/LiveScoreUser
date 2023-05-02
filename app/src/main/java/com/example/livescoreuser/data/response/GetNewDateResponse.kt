package com.example.livescoreuser.data.response

data class GetNewDateResponse(
     val tournamentId: Int,
     val tournamentName: String,
     val tournamentLogo: String,
     val groupName: String,
     val groupId: Int,
     val games:List<Games>
)
