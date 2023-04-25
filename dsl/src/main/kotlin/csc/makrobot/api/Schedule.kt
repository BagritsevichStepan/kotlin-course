package csc.makrobot.api

import csc.makrobot.dsl.MakroBotDsl

enum class WeekDay {
    Mon, Tue, Wed, Thu, Fri, Sat, Sun
}

@MakroBotDsl
class Schedule {
    internal val timePoints = arrayListOf<Pair<WeekDay, Int>>()
    internal val exceptDaysOfMonth = arrayListOf<Int>()

    override fun toString(): String {
        return buildString {
            append(timePoints.joinToString(prefix = "Schedule: ") { "${it.first} at ${it.second}h"})
            if (exceptDaysOfMonth.isNotEmpty()) {
                append(exceptDaysOfMonth.joinToString(prefix = "except on the following dates of the month: "))
            }
        }
    }
}