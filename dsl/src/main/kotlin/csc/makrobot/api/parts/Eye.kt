package csc.makrobot.api.parts

interface Eye {
    val illumination: Int
}

class LedEye(override val illumination: Int): Eye

class LampEye(override val illumination: Int): Eye

