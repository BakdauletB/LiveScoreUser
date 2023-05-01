package com.example.livescoreuser.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescoresdu.data.MatchRepository
import com.example.livescoresdu.data.MatchRepositoryImpl
import com.example.livescoresdu.data.response.TournamentUserResponse
import com.example.livescoresdu.uilibrary.values.Status
import com.example.livescoreuser.data.local.TournemntDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WelcomViewModel(private val repository: MatchRepository,private val repositoryImpl: MatchRepositoryImpl): ViewModel() {

    private val internalFavsCache = MutableStateFlow(mapOf<Int, TournamentUserResponse>())
    val favsCache =    internalFavsCache.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyMap()
    )
    val tournament: SnapshotStateList<TournamentUserResponse> = mutableStateListOf()


    var job: Job? = null
    init {
        job = viewModelScope.launch {
            getFavs().collect {
                internalFavsCache.value = it
                    .map { it.tournamentId to it }
                    .toMap()
            }

        }
    }
    fun getTournamentList(){
        if (tournament.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getTournament(1).collect {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            val result = it.data as List<TournamentUserResponse>
                            tournament.clear()
                            tournament.addAll(result)
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            }
        }
    }



    fun getFavs() = repositoryImpl.observeFavs(viewModelScope)

    fun isFavorite(imageRoot: TournamentUserResponse): Boolean = internalFavsCache.value[imageRoot.tournamentId] != null

    fun addFavorite(imageRoot: TournamentUserResponse) = viewModelScope.launch {
        repositoryImpl.addFavorite(imageRoot)
    }

    fun removeFavorite(imageRoot: TournamentUserResponse) = viewModelScope.launch {
        repositoryImpl.removeFavorite(imageRoot)
    }

}