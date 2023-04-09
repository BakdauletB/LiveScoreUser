package com.example.livescoresdu.di

import retrofit2.Response

fun <T> Response<T>.errorMessage() =
    errorBody()?.string() ?: "Unknown error"

fun <T> Response<T>.isSuccessfulAndBodyIsNotNull() =
    this.isSuccessful && this.body() != null