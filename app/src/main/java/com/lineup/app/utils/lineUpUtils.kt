package com.lineup.app.utils

import java.text.SimpleDateFormat
import java.util.*

fun getToday(): String {
    return getCurrentDayTime().toString(formatTo = "dd MMMM yyyy, E")
}

fun getCurrentDayTime(): Date {
    return Calendar.getInstance().time
}

fun Date.toString(formatTo: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(formatTo, locale)
    return formatter.format(this)
}