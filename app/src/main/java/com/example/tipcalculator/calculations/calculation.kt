package com.example.tipcalculator.calculations

fun calculation(tippercentage: Int, value: Double): Double {
    return if (value > 1 && value.toString().isNotEmpty()) {
        tippercentage * value / 100
    } else
        0.0
}