package csc.makrobot.dsl

import csc.makrobot.api.Schedule
import csc.makrobot.api.WeekDay

fun MakroBotScenario.schedule(schedule: Schedule.() -> Unit): MakroBotScenario {
    this.schedule = Schedule().apply(schedule)
    return this
}

fun MakroBotScenario.resetSchedule(): MakroBotScenario {
    schedule = null
    return this
}

typealias time = Pair<WeekDay, Int>

infix fun WeekDay.at(hour: Int) = time(this, hour)

infix fun ClosedRange<WeekDay>.at(hour: Int): List<time> =
    WeekDay.values().filter { it in this }.map { time(it, hour) }

fun Schedule.repeat(vararg timePointsToAdd: time) {
    timePoints.addAll(timePointsToAdd)
}

fun Schedule.repeat(vararg timePointsToAdd: List<time>) {
    repeat(*timePointsToAdd)
}

fun Schedule.except(vararg dayOfMonth: Int) {
    exceptDaysOfMonth.addAll(dayOfMonth.toList())
}

