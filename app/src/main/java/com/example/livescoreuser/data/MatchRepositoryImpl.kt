package com.example.livescoresdu.data

import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.example.livescore.data.local.db.LivescoreDatabase
import com.example.livescoresdu.data.request.*
import com.example.livescoresdu.data.response.*
import com.example.livescoresdu.di.errorMessage
import com.example.livescoresdu.di.isSuccessfulAndBodyIsNotNull
import com.example.livescoresdu.presentation.screens.bundle.TokenBundle
import com.example.livescoresdu.uilibrary.values.Event
import com.example.livescoreuser.data.response.GetNewDateResponse
import com.example.livescoreuser.data.response.GroupPointsResponse
import com.example.livescoreuser.data.response.TournamentSearchResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class MatchRepositoryImpl(private val dataSource: MatchService,
                          private val livescoreDatabase: LivescoreDatabase
                            ): MatchRepository {
    private lateinit var access: String



    override suspend fun getGoals(): Flow<Event<List<TeamStatisticsGoalsResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getGoals(1)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getTeamRedCards(): Flow<Event<List<TeamStatisticsGoalsResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getTeamRedCards(1)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getTeamYellowCards(): Flow<Event<List<TeamStatisticsGoalsResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getTeamYellowCards(1)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getPoints(): Flow<Event<List<PointsResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getPoints(1)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getGroupPoints(tournamentId:Int,groupId: Int): Flow<Event<List<GroupPointsResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getGroupPoints(groupId = groupId, tournamentId = tournamentId )
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getPlayerGoals(): Flow<Event<List<PlayerGoalsResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getPlayerGoals(1)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getAssists(): Flow<Event<List<PlayerGoalsResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getAssists(1)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getRedCards(): Flow<Event<List<PlayerGoalsResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getRedCards(1)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getYellowCards(): Flow<Event<List<PlayerGoalsResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getYellowCards(1)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getGameDate(date:String): Flow<Event<List<GameDateResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getGameDate(date)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getProtocol(id:Int) = flow {
        emit(Event.loading())

        val response = dataSource.getProtocol(id)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getGameId(id:Int) = flow {
        emit(Event.loading())

        val response = dataSource.getGameId(id)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getGameStart(id:Int) = flow {
        emit(Event.loading())

        val response = dataSource.getGameStart(id)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getGameEnd(id:Int) = flow {
        emit(Event.loading())

        val response = dataSource.getGameEnd(id)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun postEventGoal(body: EventGoalRequest) = flow {
        emit(Event.loading())

        val response = dataSource.postEventGoal(body = body)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun postEvent(body: EventRequest) = flow {
        emit(Event.loading())

        val response = dataSource.postEvent(body = body)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getTournament(userId: Int): Flow<Event<List<TournamentUserResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getTournament(userId = userId)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getTournamentName(name: String): Flow<Event<List<TournamentSearchResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getTournamentName(name = name)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getTeams(): Flow<Event<List<TeamResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getTeam()
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getPlayer(team_id: Int): Flow<Event<List<PlayerResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getPlayer(id = team_id)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getGroup(tournamentId: Int): Flow<Event<List<GroupResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getGroup(tournamentId = tournamentId)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun postGame(body: GameRequest) = flow {
        emit(Event.loading())
        val token = TokenBundle.token
        val response = dataSource.postGame(body = body, auth = token.orEmpty())
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getEventId(id:Int) = flow {
        emit(Event.loading())

        val response = dataSource.getEventId(id = id)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun putEventUpdate(id:Int,body: SaveGoalEventDtoRequest) = flow {
        emit(Event.loading())

        val response = dataSource.putEventUpdateGoal(id = id, body = body)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun putEvent(id:Int,body: SaveEventRequest) = flow {
        emit(Event.loading())

        val response = dataSource.putEventUpdate(id = id, body = body)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun auth(body:AuthRequest)  = flow {
        emit(Event.loading())

        val response = dataSource.auth(body = body)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            TokenBundle.token = "Bearer ${response.body()?.token}"
//            SharedPreferencesHelper.saveAccessToken(response.body()?.token.orEmpty())
//            access = "Bearer ${response.body()?.token}"
            emit(Event.success(response.body()))
//            access = "Bearer " + response.body()?.token
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun uploadFile(file: File) = flow {
        emit(Event.loading())
        val requestFile =
            file.asRequestBody(contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".toMediaTypeOrNull())
        val fileRequestBody: MultipartBody.Part =
            MultipartBody.Part.createFormData("file", file.name, requestFile)
        val response = dataSource.playerInfo(file = fileRequestBody)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
        }
        emit(Event.error(response.errorMessage()))
    }
    override suspend fun getTeamAndPlayers(): Flow<Event<List<TeamAndPlayersResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getTeamAndPlayers()
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getNewGameDate(date:String): Flow<Event<List<GetNewDateResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getGameNewDate(date)
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    override suspend fun getGameLive(): Flow<Event<List<GetNewDateResponse>>> = flow {
        emit(Event.loading())

        val response = dataSource.getGameLive()
        if (response.isSuccessfulAndBodyIsNotNull()) {
            emit(Event.success(response.body()))
            return@flow
        }

        val errorMessage = withContext(Dispatchers.IO) { response.errorBody()?.string() }
        emit(Event.error(errorMessage))
    }
    fun observeFavs(coroutineScope: CoroutineScope) = livescoreDatabase
        .TournemntDao()
        .observeFavsChanges()
        .distinctUntilChanged()
        .shareIn(coroutineScope, started = SharingStarted.Eagerly)

//    suspend fun getImage(imageId: String) = api.getImage(imageId)

    suspend fun addFavorite(imageRoot: TournamentUserResponse) = withContext(Dispatchers.IO) {
        livescoreDatabase.TournemntDao().addFav(imageRoot)
    }

    suspend fun removeFavorite(imageRoot: TournamentUserResponse) = withContext(Dispatchers.IO) {
        livescoreDatabase.TournemntDao().removeFav(imageRoot)
    }




}