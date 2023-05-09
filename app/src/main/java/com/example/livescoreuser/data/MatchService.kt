package com.example.livescoresdu.data

import com.example.livescoresdu.data.request.*
import com.example.livescoresdu.data.response.*
import com.example.livescoresdu.uilibrary.values.Constants
import com.example.livescoreuser.data.response.GetNewDateResponse
import com.example.livescoreuser.data.response.GroupPointsResponse
import com.example.livescoreuser.data.response.TournamentSearchResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface MatchService {

    @GET(Constants.GET_GOALS)
    suspend fun getGoals(
        @Query("tournament_id") tournamentId:Int
    ):Response<List<TeamStatisticsGoalsResponse>>
    @GET(Constants.GET_RED_CARDS)
    suspend fun getTeamRedCards(
        @Query("tournament_id") tournamentId:Int
    ): Response<List<TeamStatisticsGoalsResponse>>
    @GET(Constants.GET_YELLOW_CARDS)
    suspend fun getTeamYellowCards(
        @Query("tournament_id")tournamentId:Int
    ): Response<List<TeamStatisticsGoalsResponse>>

    @GET(Constants.GET_POINTS)
    suspend fun getPoints(
        @Query("tournament_id") tournamentId:Int
    ): Response<List<PointsResponse>>
    @GET(Constants.GET_GROUP_INFO)
    suspend fun getGroupPoints(
        @Query("groupId") groupId: Int,
        @Query("tournamentId") tournamentId: Int,
    ):Response<List<GroupPointsResponse>>



    @GET(Constants.GET_PLAYER_GOALS)
    suspend fun getPlayerGoals(
        @Query("tournament_id") tournamentId:Int
    ): Response<List<PlayerGoalsResponse>>
    @GET(Constants.GET_PLAYER_ASSISTS)
    suspend fun getAssists(
        @Query("tournament_id") tournamentId:Int
    ): Response<List<PlayerGoalsResponse>>
    @GET(Constants.GET_PLAYER_RED_CARD)
    suspend fun getRedCards(
        @Query("tournament_id") tournamentId:Int
    ): Response<List<PlayerGoalsResponse>>
    @GET(Constants.GET_PLAYER_YELLOW_CARD)
    suspend fun getYellowCards(
        @Query("tournament_id") tournamentId: Int
    ): Response<List<PlayerGoalsResponse>>



    @GET(Constants.GET_GAME_DATE)
    suspend fun getGameDate(
        @Query("date") date: String
    ):  Response<List<GameDateResponse>>

    @GET(Constants.GET_PROTOCOL)
    suspend fun getProtocol(
        @Path("id") id:Int
    ): Response<ProtocolResponse>





    @GET(Constants.GET_GAME_ID)
    suspend fun getGameId(
        @Path("id") id: Int
    ): Response<GameIdResponse>
    @POST(Constants.GET_GAME_START_ID)
    suspend fun getGameStart(
        @Path("id") id: Int
    ): Response<GameStartResponse>
    @POST(Constants.GET_GAME_END_ID)
    suspend fun getGameEnd(
        @Path("id") id: Int
    ): Response<GameStartResponse>
    @POST(Constants.POST_EVENT_SAVE_GOAL)
    suspend fun postEventGoal(
        @Body body: EventGoalRequest
    ): Response<EventGoalResponse>
    @POST(Constants.POST_EVENT)
    suspend fun postEvent(
        @Body body: EventRequest
    ): Response<EventResponse>

    @GET(Constants.GET_TOURNAMENT_USER)
    suspend fun getTournament(
        @Query("userId") userId:Int
    ): Response<List<TournamentUserResponse>>
    @GET(Constants.GET_TOURNAMENT_NAME)
    suspend fun getTournamentName(
        @Query("name") name:String
    ): Response<List<TournamentSearchResponse>>
    @GET(Constants.GET_TEAM)
    suspend fun getTeam(
    ): Response<List<TeamResponse>>
    @GET(Constants.GET_PLAYER)
    suspend fun getPlayer(
        @Path("team_id")id: Int,
    ):Response<List<PlayerResponse>>
    @GET(Constants.GET_GROUP)
    suspend fun getGroup(
        @Query("tournamentId")tournamentId: Int
    ):Response<List<GroupResponse>>
    @POST(Constants.POST_GAME)
    suspend fun postGame(
        @Header("Authorization")auth:String,
        @Body body: GameRequest
    ):Response<GameResponse>
    @GET(Constants.GET_EVENT_ID)
    suspend fun getEventId(
        @Path("id")id: Int
    ):Response<EventIdResponse>
    @PUT(Constants.PUT_EVENT_UPDATE_GOAL)
    suspend fun putEventUpdateGoal(
        @Path("id")id:Int,
        @Body body: SaveGoalEventDtoRequest
    ): Response<PutEventResponse>
    @PUT(Constants.PUT_EVENT_UPDATE)
    suspend fun putEventUpdate(
        @Path("id")id:Int,
        @Body body: SaveEventRequest
    ): Response<PutEventResponse>
    @POST(Constants.AUTH)
    suspend fun auth(
        @Body body:AuthRequest
    ): Response<AuthResponse>
    @Multipart
    @POST(Constants.PLAYER_INFO)
    suspend fun playerInfo(
        @Part file: MultipartBody.Part
    ):Response<Any>
    @GET(Constants.TEAM_AND_ITS_PLAYERS)
    suspend fun getTeamAndPlayers(
    ): Response<List<TeamAndPlayersResponse>>

    @GET(Constants.GET_GAME_NEW_DATE)
    suspend fun getGameNewDate(
        @Query("date") date: String
    ): Response<List<GetNewDateResponse>>
    @GET(Constants.GET_GAME_LIVE)
    suspend fun getGameLive(
    ):  Response<List<GetNewDateResponse>>





}