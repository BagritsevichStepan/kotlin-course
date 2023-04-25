package csc.makrobot.api

import csc.makrobot.api.parts.Body
import csc.makrobot.api.parts.Chassis
import csc.makrobot.api.parts.Hands
import csc.makrobot.api.parts.Head
import csc.makrobot.dsl.MakroBotDsl

@MakroBotDsl
class MakroBot(val name: String, val head: Head, val body: Body,
               val hands: Hands, val chassis: Chassis
) {
    var speed = 5
    var power = 3

    fun stepForward(steps: Int) = println("stepForward $steps")

    fun stepBack(steps: Int) = println("stepBack $steps")

    fun turnAround() = println("turnAround")

    fun pronounce(source: String) = println("pronounce:${System.lineSeparator()}$source")
}