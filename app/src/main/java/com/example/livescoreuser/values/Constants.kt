package com.example.livescoresdu.uilibrary.values

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black

object Constants {
    const val BASE_URL = "http://192.168.0.108:8081/"

    const val API_KEY_TWO = "5854abc0-36b8-11ed-b9f8-af3e9ecb0bee"

    const val GET_MATCHES = "api/v1/soccer/matches"

    const val PL_ID_MATCHES = "1980"

    const val GET_MATCH_DETAILS = "api/v1/soccer/matches/{id}"

    const val PARAM_MATCH_ID = "id"

    const val GET_STANDINGS = "api/v1/soccer/standings"

    const val GET_ODDS = "api/v1/soccer/odds/{id}"

    const val GET_SCORERS = "api/v1/soccer/topscorers"


    const val GET_GOALS = "team_statistics/goals"
    const val GET_POINTS = "team_statistics/points"
    const val GET_RED_CARDS = "team_statistics/red_cards/1"
    const val GET_YELLOW_CARDS = "team_statistics/yellow_cards/1"


    const val GET_PLAYER_ASSISTS = "player_statistics/assists"
    const val GET_PLAYER_GOALS = "player_statistics/goals"
    const val GET_PLAYER_RED_CARD = "player_statistics/red_card"
    const val GET_PLAYER_YELLOW_CARD = "player_statistics/yellow_card"

    const val GET_GAME_DATE = "game/date"
    const val GET_PROTOCOL = "protocol/{id}"

    const val GET_GAME_ID = "game/{id}"
    const val POST_GAME = "game"
    const val GET_GAME_START_ID = "game/start/{id}"
    const val GET_GAME_END_ID = "game/end/{id}"


    const val POST_EVENT_SAVE_GOAL = "event/save_goal"
    const val POST_EVENT = "event"
    const val GET_EVENT_ID = "event/{id}"

    const val PUT_EVENT_UPDATE_GOAL = "event/update_goal/{id}"
    const val PUT_EVENT_UPDATE = "event/{id}"
    const val GET_GROUP = "group/group"
    const val GET_TOURNAMENT_USER = "tournament/user"

    const val GET_TEAM = "team"

    const val GET_PLAYER = "player/team/{team_id}"

    const val AUTH = "auth/login"
    const val PLAYER_INFO = "info/upload/playerInfo"

    const val TEAM_AND_ITS_PLAYERS = "team/teamAndItsPlayers"

}
