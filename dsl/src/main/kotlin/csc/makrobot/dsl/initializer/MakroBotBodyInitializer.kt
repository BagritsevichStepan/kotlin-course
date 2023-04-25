package csc.makrobot.dsl.initializer

import csc.makrobot.api.parts.Body
import csc.makrobot.api.parts.Material
import csc.makrobot.dsl.MakroBotDsl
import csc.makrobot.exception.MaterialInitializeException

@MakroBotDsl
class MakroBotBodyInitializer(private var inscription: List<String> = emptyList()):
    Initializer<Body>, MakroBotMaterialInitializer() {

    fun inscription(initialize: InscriptionBlock.() -> Unit) {
        inscription = createAndInitialize(::InscriptionBlock, initialize)
    }

    override fun createInstance() =
        Body(material ?: throw MaterialInitializeException("Body"), inscription)
}

@MakroBotDsl
class InscriptionBlock(private var strings: MutableList<String> = arrayListOf()): Initializer<List<String>> {

    operator fun String.unaryPlus() {
        strings.add(this)
    }

    override fun createInstance() = strings
}