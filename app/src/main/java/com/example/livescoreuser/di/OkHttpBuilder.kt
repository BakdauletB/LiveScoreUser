package com.example.livescoresdu.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.livescoresdu.BuildConfig
import com.example.livescoresdu.presentation.screens.bundle.TokenBundle
import com.example.livescoresdu.uilibrary.values.Constants
import com.example.livescoresdu.uilibrary.values.Constants.BASE_URL
import domain.retrofit.interceptors.AuthInterceptor
import domain.retrofit.interceptors.ExceptionInterceptor
import domain.retrofit.interceptors.LogInterceptor
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection


class OkHttpBuilder(
    private val logInterceptor: LogInterceptor,
    private val httpLoggingInterceptor: HttpLoggingInterceptor,
    private val authInterceptor: AuthInterceptor,
    private val exceptionInterceptor: ExceptionInterceptor,
    private val chuckerInterceptor: ChuckerInterceptor
) {
    fun buildOkHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.hostnameVerifier { hostname, session ->
            return@hostnameVerifier HttpsURLConnection.getDefaultHostnameVerifier().verify(
                Constants.BASE_URL, session)
        }
        builder.setTimeout()
        builder.addInterceptor(logInterceptor)
        builder.addInterceptor(httpLoggingInterceptor)
        builder.addInterceptor {chain ->
            val request = chain.request()
            val token = TokenBundle.token.orEmpty()
            val newRequest = request.newBuilder()
                .header("Authorization", token)
                .build()
            chain.proceed(newRequest)
        }
        builder.addInterceptor(exceptionInterceptor)
        builder.addInterceptor(chuckerInterceptor)
        builder.retryOnConnectionFailure(true)

        return builder.build()
    }
}

private const val TIMEOUT = 60L

private fun OkHttpClient.Builder.setTimeout(): OkHttpClient.Builder {
    connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    readTimeout(TIMEOUT, TimeUnit.SECONDS)
    writeTimeout(TIMEOUT, TimeUnit.SECONDS)

    return this
}