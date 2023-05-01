package com.example.livescoreuser.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.livescoresdu.data.response.TournamentUserResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface TournemntDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFav(image: TournamentUserResponse)

    @Delete
    suspend fun removeFav(image: TournamentUserResponse)

    @Query("SELECT * FROM tournament")
    fun observeFavsChanges(): Flow<List<TournamentUserResponse>>

}