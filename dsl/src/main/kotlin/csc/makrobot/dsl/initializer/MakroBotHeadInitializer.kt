package csc.makrobot.dsl.initializer

import csc.makrobot.api.parts.*
import csc.makrobot.dsl.MakroBotDsl
import csc.makrobot.exception.EyesInitializeException
import csc.makrobot.exception.MaterialInitializeException
import csc.makrobot.exception.MouthInitializeException
import csc.makrobot.exception.SpeakerInitializeException
import kotlin.reflect.KFunction
import kotlin.reflect.full.primaryConstructor

@MakroBotDsl
class MakroBotHeadInitializer(private var eyes: List<Eye>? = null, private var mouth: Mouth? = null):
    Initializer<Head>, MakroBotMaterialInitializer() {

    fun eyes(initialize: MakroBotEyesInitializer.() -> Unit) {
        eyes = createAndInitialize(::MakroBotEyesInitializer, initialize)
    }

    fun mouth(initialize: MakroBotMouthInitializer.() -> Unit) {
        mouth = createAndInitialize(::MakroBotMouthInitializer, initialize)
    }

    override fun createInstance() = Head(
        material ?: throw MaterialInitializeException("Head"),
        eyes ?: throw EyesInitializeException("Head"),
        mouth ?: throw MouthInitializeException("Head")
    )
}

@MakroBotDsl
class MakroBotEyesInitializer(var eyes: MutableList<Eye> = arrayListOf()): Initializer<List<Eye>> {
    val ledEyes: LedEye = LedEye(0)
    val lampEyes: LampEye = LampEye(0)

    inline operator fun <reified T: Eye> T.invoke(
        noinline initialize: MakroBotSpecificEyesInitializer<T>.() -> Unit) {
        eyes.addAll(createAndInitialize(
            { MakroBotSpecificEyesInitializer(T::class.primaryConstructor!!) },
            initialize
        ))
    }

    override fun createInstance() = eyes
}

@MakroBotDsl
class MakroBotSpecificEyesInitializer<T: Eye> (private val eyeFactory: KFunction<T>, var count: Int = 0,
                                               var illumination: Int = 0): Initializer<List<T>> {

    override fun createInstance(): List<T> {
        val eyes = mutableListOf<T>()
        repeat(count) {
            eyes.add(eyeFactory.call(illumination))
        }
        return eyes
    }
}

@MakroBotDsl
class MakroBotMouthInitializer(private var speaker: Speaker? = null): Initializer<Mouth> {
    fun speaker(initialize: MakroBotSpeakerInitializer.() -> Unit) {
        speaker = createAndInitialize(::MakroBotSpeakerInitializer, initialize)
    }

    override fun createInstance() = Mouth(speaker ?: throw SpeakerInitializeException("Mouth"))
}

@MakroBotDsl
class MakroBotSpeakerInitializer(var power: Int = 0): Initializer<Speaker> {
    override fun createInstance() = Speaker(power)
}

