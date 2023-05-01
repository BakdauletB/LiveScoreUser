package com.example.livescoresdu.uilibrary.values

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

private const val NAME = "name"
private const val PHONE_NUMBER = "phone_number"
private const val EMAIL = "email"
private const val HIDE_BALANCE_STATUS = "hide_balance_status"
private const val HIDE_BALANCE_GESTURE_STATUS = "hide_balance_gesture_status"
private const val PIN = "pin"
private const val LOCALE = "locale"
private const val APP_THEME = "app_theme"
private const val APP_ICON = "app_icon"
private const val FINGERPRINT_ENABLED = "fingerprint_enabled"
private const val NOTIFICATION_ENABLED = "notification_enabled"
private const val GEOLOCATION_ENABLED = "geolocation_enabled"
private const val ALERT_ENABLED = "alert_enabled"
private const val CARD_THEME = "card_theme"

private const val TOKEN_TYPE = "token_type"
private const val FIREBASE_PUSH_TOKEN = "firebase_push_token"
private const val DEVICE_ID = "device_id"
private const val SALT = "salt"
private const val REFRESH_TOKEN = "refresh_token"
private const val ACCESS_TOKEN = "access_token"
private const val REGISTRATION_ID = "registration_id"
private const val EGOV_PROCESS_ID = "egov_process_id"
private const val HIDE_BALANCE_HINT_NOT_SHOW = "hide_balance_hint_not_show"
private const val REGISTRATION_TYPE = "REGISTRATION_TYPE"

private const val CITY_NAME = "city_name"

object SharedPreferencesHelper {

    private lateinit var preferences: SharedPreferences
    private lateinit var firebasePreferences: SharedPreferences


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    }
    var city: String
        get() = preferences.getString(CITY_NAME, "").orEmpty()
        set(value) {
            preferences.edit().putString(CITY_NAME, value).apply()
        }

    var registrationType: String
        get() = preferences.getString(REGISTRATION_TYPE, "").orEmpty()
        set(value) {
            preferences.edit().putString(REGISTRATION_TYPE, value).apply()
        }

    fun init(commonPreferences: SharedPreferences, firebasePreferences: SharedPreferences) {
        this.preferences = commonPreferences
        this.firebasePreferences = firebasePreferences
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    fun saveHideBalanceHint() {
        preferences.edit().putBoolean(HIDE_BALANCE_HINT_NOT_SHOW, false).apply()
    }

    fun needShowBalanceHint(): Boolean = preferences.getBoolean(HIDE_BALANCE_HINT_NOT_SHOW, true)

    fun saveFullName(fullName: String) {
        preferences.edit().putString(NAME, fullName).apply()
    }

    fun savePhoneNumber(phoneNumber: String) {
        preferences.edit().putString(PHONE_NUMBER, phoneNumber).apply()
    }

    fun saveRegistrationId(processInstanceId: String) {
        preferences.edit().putString(REGISTRATION_ID, processInstanceId).apply()
    }

    fun saveEmail(email: String) {
        preferences.edit().putString(EMAIL, email).apply()
    }

    fun savePin(pin: String) {
        preferences.edit().putString(PIN, pin).apply()
    }

    fun saveLocale(locale: String) {
        preferences.edit().putString(LOCALE, locale).apply()
    }


    fun saveAppIcon(appIcon: String) {
        preferences.edit().putString(APP_ICON, appIcon).apply()
    }

    fun saveFingerprintEnabled(fingerprintEnabled: Boolean) {
        preferences.edit().putBoolean(FINGERPRINT_ENABLED, fingerprintEnabled).apply()
    }

    fun saveGeolocationEnabled(geolocationEnabled: Boolean) {
        preferences.edit().putBoolean(GEOLOCATION_ENABLED, geolocationEnabled).apply()
    }

    fun saveWelcome(notificationEnabled: Boolean) {
        preferences.edit().putBoolean(NOTIFICATION_ENABLED, notificationEnabled).apply()
    }

    fun saveAlertEnabled(alertEnabled: Boolean) {
        preferences.edit().putBoolean(ALERT_ENABLED, alertEnabled).apply()
    }


    fun saveFirebasePushToken(token: String) {
        firebasePreferences.edit().putString(FIREBASE_PUSH_TOKEN, token).apply()
    }

    fun saveDeviceId(deviceId: String) {
        preferences.edit().putString(DEVICE_ID, deviceId).apply()
    }

    fun saveSalt(salt: String) {
        preferences.edit().putString(SALT, salt).apply()
    }

    fun saveRefreshToken(token: String) {
        preferences.edit().putString(REFRESH_TOKEN, token).apply()
    }

    fun saveAccessToken(accessToken: String) {
        preferences.edit().putString(ACCESS_TOKEN, accessToken).apply()
    }

    fun saveTokenType(tokenType: String) {
        preferences.edit().putString(TOKEN_TYPE, tokenType).apply()
    }

    fun saveEgovProcessId(processId: String) {
        preferences.edit().putString(EGOV_PROCESS_ID, processId).apply()
    }

    fun saveHideBalanceStatus(isHideBalanceEnabled: Boolean) {
        Log.e("saveHideBalanceStatus", "saveHideBalanceStatus = $isHideBalanceEnabled")
        preferences.edit().putBoolean(HIDE_BALANCE_STATUS, isHideBalanceEnabled).apply()
    }

    fun saveHideBalanceGestureStatus(isHideBalanceGestureEnabled: Boolean) {
        preferences.edit().putBoolean(HIDE_BALANCE_GESTURE_STATUS, isHideBalanceGestureEnabled)
            .apply()
    }

    fun getFullName() = preferences.getString(NAME, null)
    fun getPhoneNumber() = preferences.getString(PHONE_NUMBER, null)
    fun getEmail() = preferences.getString(EMAIL, null)
    fun getPin() = preferences.getString(PIN, null)
    fun getLocale() = preferences.getString(LOCALE, null)

    fun getWelcome() =
        preferences.getBoolean(NOTIFICATION_ENABLED, false)

    fun getFingerprintEnabled() =
        preferences.getBoolean(FINGERPRINT_ENABLED, false)

    fun getGeolocationEnabled() =
        preferences.getBoolean(GEOLOCATION_ENABLED, false)

    fun getAlertEnabled() = preferences.getBoolean(ALERT_ENABLED, false)
    //preferences.getString(cardId, null)
    fun getFirebasePushToken(): String {
        return firebasePreferences.getString(FIREBASE_PUSH_TOKEN, "").orEmpty()
    }

    fun getDeviceId() = preferences.getString(DEVICE_ID, "")
    fun getRefreshToken() = preferences.getString(REFRESH_TOKEN, "")
    fun getAccessToken() = preferences.getString(ACCESS_TOKEN, "")
    fun getSalt() = preferences.getString(SALT, "")
    fun getTokenType() = preferences.getString(TOKEN_TYPE, "")
    fun getRegistrationId() = preferences.getString(REGISTRATION_ID, "")
    fun getEgovProcessId() = preferences.getString(EGOV_PROCESS_ID, "")
    fun getHideBalanceStatus() = preferences.getBoolean(
        HIDE_BALANCE_STATUS,
        false
    )

    fun getHideBalanceGestureStatus() = preferences.getBoolean(
        HIDE_BALANCE_GESTURE_STATUS,
        false
    )



}

