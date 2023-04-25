package csc.makrobot.api.parts

interface Material {
    var thickness: Int
}

class Metal(override var thickness: Int = 0): Material

class Plastic(override var thickness: Int = 0): Material
