package csc.makrobot.dsl.initializer

import csc.makrobot.api.*
import csc.makrobot.api.parts.Body
import csc.makrobot.api.parts.Chassis
import csc.makrobot.api.parts.Hands
import csc.makrobot.api.parts.Head
import csc.makrobot.dsl.MakroBotDsl
import csc.makrobot.exception.BodyInitializeException
import csc.makrobot.exception.ChassisInitializeException
import csc.makrobot.exception.HandsInitializeException
import csc.makrobot.exception.HeadInitializeException

@MakroBotDsl
class MakroBotInitializer(private val name: String, private var head: Head? = null,
                          private var body: Body? = null, private var hands: Hands? = null,
                          var chassis: Chassis? = null): Initializer<MakroBot> {
    val caterpillar: Chassis.Caterpillar = Chassis.Caterpillar(0)
    val legs: Chassis.Legs = Chassis.Legs
    val wheels: Chassis.Wheels = Chassis.Wheels(0, 0)

    fun head(initialize: MakroBotHeadInitializer.() -> Unit) {
        head = createAndInitialize(::MakroBotHeadInitializer, initialize)
    }

    fun body(initialize: MakroBotBodyInitializer.() -> Unit) {
        body = createAndInitialize(::MakroBotBodyInitializer, initialize)
    }

    fun hands(initialize: MakroBotHandsInitializer.() -> Unit) {
        hands = createAndInitialize(::MakroBotHandsInitializer, initialize)
    }

    infix fun Chassis.Caterpillar.width(width: Int): Chassis.Caterpillar {
        this.width = width
        return this
    }

    operator fun Chassis.Wheels.invoke(settings: Chassis.Wheels.() -> Unit): Chassis.Wheels {
        this.apply(settings)
        return this
    }

    override fun createInstance() = MakroBot(
        name,
        head ?: throw HeadInitializeException("Robot"),
        body ?: throw BodyInitializeException("Robot"),
        hands ?: throw HandsInitializeException("Robot"),
        chassis ?: throw ChassisInitializeException("Robot")
    )
}

fun robot(name: String = "", initialize: MakroBotInitializer.() -> Unit) =
    createAndInitialize({ MakroBotInitializer(name) }, initialize)