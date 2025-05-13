package com.homefirst.Library.utils

import java.text.SimpleDateFormat
import java.util.*

enum class DateTimeFormat(val value: String) {

    yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
    dd_MM_yyyy_HH_mm_ss("dd-MM-yyyy HH:mm:ss"),
    dd_MM_yyyy_hh_mm_a("dd-MM-yyyy hh:mm a"),
    d_EEE_yyyy("d MMM, yyyy"),
    d_MMM_yyyy_hh_mm_a("d MMM, yyyy hh:mm a"),
    ddMMyyyy("ddMMyyyy"),
    d_EEE_yyyy_hh_mm_a("d MMM, yyyy hh:mm a")
}

enum class DateTimeZone(val value: String) {
    IST("IST"), GMT("GMT");
}

object DateTimeUtils {
    fun getCurrentDateTimeInIST(): String = getCurrentDateTimeInIST(DateTimeFormat.yyyy_MM_dd_HH_mm_ss)

    fun getCurrentDateTimeInIST(dateTimeFormat: DateTimeFormat): String {
        val dt = Date()
        val sdf = SimpleDateFormat(dateTimeFormat.value)
        sdf.timeZone = TimeZone.getTimeZone(DateTimeZone.IST.value)
        return sdf.format(dt)
    }
}