package com.example.livescoresdu.di


import com.example.livescoresdu.presentation.viewmodels.*
import com.fvalela.calendarjetpackcompose.sample.viewmodel.CalendarViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val transfersFeatureModule = module {
    includes(transfersRepositoryModule,)

    viewModel { MatchesViewModel(repository = get()) }
    viewModel { MatchesDetailViewModel(repository = get()) }
    viewModel { MatchDetailAdminViewModel(repository = get()) }
    viewModel { CalendarViewModel() }
    viewModel { AuthViewModel(get())}
    viewModel { FavorutiesViewModel(get())}
    viewModel { ProfileViewModel(get()) }

}