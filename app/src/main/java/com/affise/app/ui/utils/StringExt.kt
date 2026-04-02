package com.affise.app.ui.utils

import java.text.SimpleDateFormat
import java.util.Locale


private val camelPattern = "(?<=.)([A-Z]|\\d+)".toRegex()
fun String.toNormalCase(): String {
    return this.replace(camelPattern, " $0").lowercase().trimStart()
}

fun String.toTimestamp(format: String = "dd.MM.yyyy"): Long? {
    return SimpleDateFormat(format, Locale.getDefault()).parse(this)?.time
}