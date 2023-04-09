package com.example.livescore.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import com.example.livescore.R
import com.example.livescore.presentation.FilterContent

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
    const val GET_GAME_START_ID = "game/start/{id}"

    const val POST_EVENT_SAVE_GOAL = "event/save_goal"

    const val GET_TOURNAMENT_USER = "tournament/user"

    const val GET_TEAM = "team"

    const val PARAM_ODD_ID = "id"

    val FILTER_CONTENT_LIST = listOf(
        FilterContent(R.drawable.premier_logo, Color.White, R.color.pink, "Premier League"),
        FilterContent(R.drawable.mylogo, Black, R.color.white, "Laliga"),
        FilterContent(R.drawable.test, Black, R.color.white, "Serie A"),
        FilterContent(R.drawable.league, Black, R.color.white, "League 1"),
        FilterContent(R.drawable.league, Black, R.color.white, "League 1")
    )
}
