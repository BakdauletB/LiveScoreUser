package com.example.livescoreuser.data.response

data class GetNewDateResponse(
     val tournamentName: String,
     val tournamentLogo: String,
     val groupName: String,
     val groupId: Int,
     val games:List<Games>
)
