package com.example.livescore.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.livescoresdu.data.response.TournamentUserResponse
import com.example.livescoreuser.data.local.TournemntDao

@Database(
    entities = [TournamentUserResponse::class],
    version = 1,
    exportSchema = false
)
abstract class LivescoreDatabase : RoomDatabase() {


    abstract fun TournemntDao(): TournemntDao
}
