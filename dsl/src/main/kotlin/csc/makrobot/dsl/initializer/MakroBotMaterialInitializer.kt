package csc.makrobot.dsl.initializer

import csc.makrobot.api.parts.Material
import csc.makrobot.api.parts.Metal
import csc.makrobot.api.parts.Plastic

open class MakroBotMaterialInitializer(internal var material: Material? = null) {
    val metal: Metal = Metal()
    val plastic: Plastic = Plastic()

    infix fun <T: Material> T.withThickness(thickness: Int) {
        this.thickness = thickness
        material = this
    }
}