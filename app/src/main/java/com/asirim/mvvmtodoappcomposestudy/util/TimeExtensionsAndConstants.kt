package com.asirim.mvvmtodoappcomposestudy.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val NO_TIME_INFO = "No time info"
const val DECIDED_AT = "Decided at: "
const val COMPLETED_AT = "Completed at: "

fun Date.formatDateToLocalString() = SimpleDateFormat(
    "HH.mm - dd.MM.yyyy",
    Locale.getDefault()
).format(this) ?: NO_TIME_INFO