package com.example.livescoresdu.di

import android.content.Context
import com.example.livescoresdu.data.MatchRepository
import com.example.livescoresdu.data.MatchRepositoryImpl
import com.example.livescoresdu.data.MatchService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit




internal val transfersRepositoryModule = module {
    includes(networkModule)

    single { get<Retrofit>().create(MatchService::class.java) }
    single<MatchRepository> { MatchRepositoryImpl(get()) }
}

