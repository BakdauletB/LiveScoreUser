package com.example.livescoresdu.uilibrary.values

data class Event<out T>(val status: Status, val data: T? = null, val message: String? = null) {
    companion object {
        fun <T> loading(): Event<T> {
            return Event(Status.LOADING, null, null)
        }

        fun <T> success(data: T?, message: String? = null): Event<T> {
            return Event(Status.SUCCESS, data, message)
        }

        fun <T> error(message: String?, data: T? = null): Event<T> {
            return Event(Status.ERROR, data, message)
        }
    }
}