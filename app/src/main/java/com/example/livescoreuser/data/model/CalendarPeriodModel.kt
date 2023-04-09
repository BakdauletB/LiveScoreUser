package com.example.livescoresdu.data.model

import java.text.SimpleDateFormat
import java.util.*

class CalendarPeriodModel(
    val type: Periods
) {
    private var selectedDate: Date? = null
    private var allTime: Date? = null
    private var select:String? = null


    fun getTitle(): Date?  {
        return when (type) {

            Periods.THU -> {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_WEEK, -2)
                calendar.time
            }
            Periods.FRI -> {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_WEEK, -1)
                calendar.time
            }
            Periods.TODAY -> {
                val calendar = Calendar.getInstance()
                calendar.time
            }
            Periods.SUN -> {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                calendar.time
            }
            Periods.MON -> {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_WEEK, +2)
                calendar.time
            }
            else -> {
                val calendar = Calendar.getInstance()
                calendar.time
            }
        }
    }
//    fun getDateAsString(): String {
//        val date = getDate()
//        val fromCalendar = Calendar.getInstance()
//        //        val to = date.second?.let { simpleDateFormat.format(it) }
//        val format = "dd MMM"
//        date?.let { fromCalendar.time = it }
//        val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
//        val from = date?.let { simpleDateFormat.format(it) }
//        return "$from"
//    }
    fun getTitleAsString(): String{
        return when (type){
            Periods.THU -> {
                val date = getTitle()
                val fromCalendar = Calendar.getInstance()
                fromCalendar.add(Calendar.DAY_OF_WEEK, -2)
                val format = "EEEEEEE"
                date?.let { fromCalendar.time = it }
                val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
                val from = date?.let { simpleDateFormat.format(it) }
                return "${from?.toUpperCase()}"
            }
            Periods.FRI -> {
                val date = getTitle()
                val fromCalendar = Calendar.getInstance()
                fromCalendar.add(Calendar.DAY_OF_WEEK, -1)
                val format = "EEEEEEE"
                date?.let { fromCalendar.time = it }
                val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
                val from = date?.let { simpleDateFormat.format(it) }
                return "${from?.toUpperCase()}"
            }
            Periods.TODAY -> {
                val date = getTitle()
                val fromCalendar = Calendar.getInstance()
                fromCalendar.add(Calendar.DAY_OF_WEEK, -1)
                val format = "EEEEEEE"
                date?.let { fromCalendar.time = it }
                val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
                val from = date?.let { simpleDateFormat.format(it) }
                return "TODAY"
            }
            Periods.SUN -> {
                val date = getTitle()
                val fromCalendar = Calendar.getInstance()
                fromCalendar.add(Calendar.DAY_OF_WEEK, +1)
                val format = "EEEEEEE"
                date?.let { fromCalendar.time = it }
                val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
                val from = date?.let { simpleDateFormat.format(it) }
                return "${from?.toUpperCase()}"
            }
            Periods.MON -> {
                val date = getTitle()
                val fromCalendar = Calendar.getInstance()
                fromCalendar.add(Calendar.DAY_OF_WEEK, +2)
                val format = "EEEEEEE"
                date?.let { fromCalendar.time = it }
                val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
                val from = date?.let { simpleDateFormat.format(it) }
                return "${from?.toUpperCase()}"
            }
        }
    }

    fun getDate(): Date?{
        return when (type) {
            Periods.THU -> {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_WEEK, -2)
                calendar.time
            }
            Periods.FRI -> {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_WEEK, -1)
                calendar.time
            }
            Periods.TODAY -> {
                val calendar = Calendar.getInstance()
                calendar.time
            }
            Periods.SUN -> {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                calendar.time
            }
            Periods.MON -> {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_WEEK, +2)
                calendar.time
            }
//            Periods.SELECT_PERIOD -> selectedDate
        }
    }
    fun getDateAsString(): String {
        val date = getDate()
        val fromCalendar = Calendar.getInstance()
        //        val to = date.second?.let { simpleDateFormat.format(it) }
        val format = "dd MMM"
        date?.let { fromCalendar.time = it }
        val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        val from = date?.let { simpleDateFormat.format(it) }
        return "${from?.toUpperCase()}"
    }


    fun setSelectedDate(date: Date?) {
//        if (date.second == null) {
//            val pair = Pair(date.first, date.first)
//            selectedDate = pair
//        }
        val fromCalendar = Calendar.getInstance()
        date.let { fromCalendar.time = it}
        val format = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        val from = date?.let { simpleDateFormat.format(it) }
        select = from
    }

    fun setAllTime(date: Date?) {
        allTime = date
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CalendarPeriodModel

        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}

enum class Periods {
    THU, FRI, TODAY, SUN, MON,
}

fun convertDate(date: String, fromFormat: String, toFormat: String): String {
    val parser = SimpleDateFormat(fromFormat, Locale.getDefault())
    val formatter = SimpleDateFormat(toFormat, Locale.getDefault())
    return try {
        parser.parse(date)?.let { formatter.format(it) } ?: ""
    } catch (e: Exception) {
        ""
    }
}

