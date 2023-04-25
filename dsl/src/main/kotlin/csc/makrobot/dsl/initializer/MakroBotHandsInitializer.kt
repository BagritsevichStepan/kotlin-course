package csc.makrobot.dsl.initializer

import csc.makrobot.api.parts.*
import csc.makrobot.dsl.MakroBotDsl
import csc.makrobot.exception.MaterialInitializeException

@MakroBotDsl
class MakroBotHandsInitializer(var load: Pair<Load, Load> = Pair(getMinLoad(), getMaxLoad())):
    Initializer<Hands>, MakroBotMaterialInitializer() {

    operator fun Load.minus(otherLoad: Load): Pair<Load, Load> = this to otherLoad

    override fun createInstance() =
        Hands(material ?: throw MaterialInitializeException("Hands"), load.first, load.second)
}