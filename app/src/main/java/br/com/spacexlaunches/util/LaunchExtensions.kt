package br.com.spacexlaunches.util

import android.view.View
import android.widget.TextView
import br.com.spacexlaunches.R
import br.com.spacexlaunches.base.api.models.Launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun Launch.setLaunchStatusOnTextView(textView: TextView) {
    when {
        upcoming == true -> {
            textView.visibility = View.VISIBLE
            textView.text = textView.context.getString(R.string.list_item_launch_status_upcoming)
            textView.setBackgroundResource(R.drawable.bg_upcoming)
        }
        launchSuccess == true -> {
            textView.visibility = View.VISIBLE
            textView.text = textView.context.getString(R.string.list_item_launch_status_success)
            textView.setBackgroundResource(R.drawable.bg_success)
        }
        launchSuccess == false -> {
            textView.visibility = View.VISIBLE
            textView.text = textView.context.getString(R.string.list_item_launch_status_failed)
            textView.setBackgroundResource(R.drawable.bg_failed)
        }
        else -> {
            textView.text = null
            textView.visibility = View.GONE
        }
    }
}

fun Launch.setFormattedDateOnTextView(textView: TextView) {
    val serverUtcDateFormat = "yyyy-MM-dd'T'HH:mm:ss.000'Z'"
    val launchListDateFormat = "MMMM dd, yyyy - HH:mm"
    val locale = Locale.UK

    try {
        val inputDateFormat = SimpleDateFormat(serverUtcDateFormat, locale)
        val outputDateFormat = SimpleDateFormat(launchListDateFormat, locale)
        val date = inputDateFormat.parse(launchDateUtc)
        textView.text = outputDateFormat.format(date)
    } catch (exception: ParseException) {
        textView.text = launchDateUtc
    }
}
