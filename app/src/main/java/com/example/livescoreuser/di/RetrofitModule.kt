 package com.example.livescoresdu.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.livescoresdu.uilibrary.values.Constants
import com.google.gson.GsonBuilder
import com.example.livescoreuser.di.interceptors.AuthInterceptor
import domain.retrofit.interceptors.ExceptionInterceptor
import domain.retrofit.interceptors.LogInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


 internal val gsonInstanceModule = module {
    single { GsonBuilder().setLenient().create() }
}
internal val enumConverterInstanceModule = module {
    single { EnumConverterFactory() }
}

internal val interceptorsModule = module {
    factory { LogInterceptor() }
    factory { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
    factory { AuthInterceptor() }
    factory { ExceptionInterceptor() }
    factory {
        ChuckerInterceptor.Builder(get())
            .collector(ChuckerCollector(get()))
            .build()
    }
}

internal val okHttpBuilderInstance = module {
    includes(interceptorsModule)
    single { OkHttpBuilder(get(), get(), get(), get(), get()).buildOkHttpClient() }
}

val networkModule = module {
    includes(gsonInstanceModule)
    includes(enumConverterInstanceModule)
    includes(okHttpBuilderInstance)

    single<Retrofit> {
        val gsonConverterFactory = GsonConverterFactory.create(get())
        val enumConverterFactory: EnumConverterFactory = get()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(ScalarsConverterFactory.create()) //important
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
//            .client(get())
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(gsonConverterFactory)
//            .addConverterFactory(enumConverterFactory)
//            .build()
    }
}