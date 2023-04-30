package com.example.livescoresdu.data

import com.example.livescoresdu.data.request.*
import com.example.livescoresdu.data.response.*
import com.example.livescoresdu.uilibrary.values.Event
import com.example.livescoreuser.data.response.GetNewDateResponse
import kotlinx.coroutines.flow.Flow
import java.io.File

interface MatchRepository {

    suspend fun getGoals(): Flow<Event<List<TeamStatisticsGoalsResponse>>>
    suspend fun getTeamRedCards(): Flow<Event<List<TeamStatisticsGoalsResponse>>>
    suspend fun getTeamYellowCards(): Flow<Event<List<TeamStatisticsGoalsResponse>>>
    suspend fun getPoints(): Flow<Event<List<PointsResponse>>>
    suspend fun getPlayerGoals(): Flow<Event<List<PlayerGoalsResponse>>>
    suspend fun getAssists(): Flow<Event<List<PlayerGoalsResponse>>>
    suspend fun getRedCards(): Flow<Event<List<PlayerGoalsResponse>>>
    suspend fun getYellowCards(): Flow<Event<List<PlayerGoalsResponse>>>
    suspend fun getGameDate(date:String): Flow<Event<List<GameDateResponse>>>
    suspend fun getProtocol(id: Int): Flow<Event<Any>>
    suspend fun getGameId(id:Int): Flow<Event<Any>>
    suspend fun getGameStart(id:Int): Flow<Event<Any>>
    suspend fun getGameEnd(id: Int):  Flow<Event<Any>>
    suspend fun postEventGoal(body: EventGoalRequest): Flow<Event<Any>>
    suspend fun postEvent(body: EventRequest): Flow<Event<Any>>
    suspend fun getTournament(userId:Int): Flow<Event<List<TournamentUserResponse>>>
    suspend fun getTeams(): Flow<Event<List<TeamResponse>>>
    suspend fun getPlayer(team_id: Int): Flow<Event<List<PlayerResponse>>>
    suspend fun getGroup(tournamentId: Int): Flow<Event<List<GroupResponse>>>
    suspend fun postGame(body: GameRequest): Flow<Event<GameResponse>>
    suspend fun getEventId(id: Int): Flow<Event<EventIdResponse>>
    suspend fun putEventUpdate(id: Int,body: SaveGoalEventDtoRequest): Flow<Event<PutEventResponse>>
    suspend fun putEvent(id: Int,body: SaveEventRequest): Flow<Event<PutEventResponse>>
    suspend fun auth(body:AuthRequest): Flow<Event<AuthResponse>>
    suspend fun uploadFile(file: File): Flow<Event<Any>>
    suspend fun getTeamAndPlayers():Flow<Event<List<TeamAndPlayersResponse>>>

    suspend fun getNewGameDate(date:String): Flow<Event<List<GetNewDateResponse>>>
    suspend fun getGameLive(): Flow<Event<List<GetNewDateResponse>>>

}