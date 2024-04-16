package com.example.techiebutlertest.domain.util

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan

fun makeFirstLetterCapital(text: String): String {
    return text.split(' ').joinToString(" ") {
        it.replaceFirstChar { char ->
            char.uppercase()
        }
    }
}

fun getSpanData(text: String): Spannable {
    val sb = SpannableStringBuilder(text)
    val bss = StyleSpan(Typeface.BOLD)
    sb.setSpan(bss, 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    return sb
}