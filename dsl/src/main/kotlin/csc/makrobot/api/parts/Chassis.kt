package csc.makrobot.api.parts

sealed class Chassis {
    class Wheels(var count: Int, var diameter: Int): Chassis()
    object Legs : Chassis()
    class Caterpillar(var width: Int): Chassis()
}