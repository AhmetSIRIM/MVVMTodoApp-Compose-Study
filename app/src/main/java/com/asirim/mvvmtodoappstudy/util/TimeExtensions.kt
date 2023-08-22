package com.asirim.mvvmtodoappstudy.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val NO_TIME_INFO = "No time info"

fun Date.formatDateToLocalString() = SimpleDateFormat(
    "HH.mm - dd.MM.yyyy",
    Locale.getDefault()
).format(this) ?: NO_TIME_INFO