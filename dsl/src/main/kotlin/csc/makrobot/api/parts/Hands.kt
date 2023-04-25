package csc.makrobot.api.parts

class Hands(val material: Material, val minLoad: Load, val maxLoad: Load)

enum class Load {
    VeryLight, Light, Medium, Heavy, VeryHeavy, Enormous
}

fun getMinLoad(): Load = Load.values().first()

fun getMaxLoad(): Load = Load.values().last()
