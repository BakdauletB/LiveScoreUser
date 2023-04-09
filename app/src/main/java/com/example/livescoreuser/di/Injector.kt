package com.example.livescoresdu.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal fun configureKoin(context: Context) {
    startKoin {
        androidContext(context)

        modules(
            transfersFeatureModule
        )
    }
}
