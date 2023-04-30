package com.example.livescoresdu.uilibrary.values

import com.example.livescoreuser.R


enum class MainDestinations(val title: String, val destination: String, val icon: Int = 0) {
    HOME("Scores", "home", R.drawable.ic_baseline_sports_football_24),
    OPERATIONS("Favorites", "payments", R.drawable.ic_baseline_star_outline_24),
    SERVICES("Profile", "profile", R.drawable.baseline_person_24),
//    CHAT("Сообщения", "chat", R.drawable.ic_chat),
//    MORE("Еще", "more", R.drawable.ic_more),
    PROFILE("", destination = "profile"),
    MAIN("", destination = "main"),
    FOREX_STORIES("", destination = "forexStories"),
    BACK("", destination = "back")
}