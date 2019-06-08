package com.stain.Notez

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

object Util {

    fun getInstant(): Instant {
        return Instant.now()
    }

    fun getInstantFromString(data: String): Instant {
        return Instant.parse(data)
    }

    fun getTimestamp(): String {
        return getInstant().toString()
    }

    fun getFormattedTimestamp(data: String): String {
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault())

        return formatter.format(getInstantFromString(data))
    }

    /**
     * Note: Function should be moved elsewhere.
     */
    fun getTimestampText(data: String): String {
        return if (data == "") "Updated: ----" else "Updated: ${getFormattedTimestamp(data)}"
    }

//    fun getLocalDateTime(): LocalDateTime {
//        return LocalDateTime.now()
//    }

//    const val format = "yyyy-MM-dd HH:mm:ssZ"
//
//    fun getStringFromCalendar(calendar: Calendar): String {
//        val tzformat = TimeZoneFormat.getInstance(Locale.getDefault())
//        val mFormat = tzformat.gmtZeroFormat
//
//        Log.e("Util.getStringFromCalendar", "mFormat: $mFormat")
//
//        val sdf = SimpleDateFormat(format)
//        return sdf.format(calendar.time)
//    }
//
//    fun getCalendarFromString(data: String): Calendar {
//        val calendar = Calendar.getInstance()
//        val sdf = SimpleDateFormat(format)
//
//        calendar.time = sdf.parse(data)
//        return calendar
//    }
//
//    fun calendarUtcNow(): Calendar {
//        return Calendar.getInstance(timeZoneUtc()) // This changes the default TimeZone for all Calendar.getInstance calls. But other calls still work, why?
//    }
//
//    fun timeZoneUtc(): TimeZone {
//        return TimeZone.getTimeZone("UTC")
//    }
//
//    fun sdmf() {
//        ZonedDateTime.now()
//    }
}