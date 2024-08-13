package com.openaiinvest.app.presentation.main.invest.util

import java.text.DecimalFormat

fun roundToTwoDecimalPlaces(number: Double): Double {
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(number).toDouble()
}