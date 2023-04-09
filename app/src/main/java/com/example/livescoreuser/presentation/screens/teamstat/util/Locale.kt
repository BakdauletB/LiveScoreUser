package com.example.livescore.util

import com.example.livescore.presentation.SharedPreferencesHelper
import java.util.*

fun getLocale() = if (SharedPreferencesHelper.getLocale() == null) {
    Locale.getDefault()
} else {
    Locale(SharedPreferencesHelper.getLocale() ?: "ru")
}