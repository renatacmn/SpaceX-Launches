package br.com.spacexlaunches.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateUtil @Inject constructor(private val locale: Locale) {

    fun parseDate(inputDateStr: String?): String? {
        if (inputDateStr.isNullOrBlank()) return null
        return try {
            val inputDateFormat = SimpleDateFormat(SERVER_UTC_DATE_FORMAT, locale)
            val outputDateFormat = SimpleDateFormat(LAUNCH_LIST_DATE_FORMAT, locale)
            val date = inputDateFormat.parse(inputDateStr)
            outputDateFormat.format(date)
        } catch (exception: ParseException) {
            inputDateStr
        }
    }

    companion object {
        private const val SERVER_UTC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.000'Z'"
        private const val LAUNCH_LIST_DATE_FORMAT = "MMMM dd, yyyy - HH:mm"
    }

}
