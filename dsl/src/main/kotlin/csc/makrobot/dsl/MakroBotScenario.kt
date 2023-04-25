package csc.makrobot.dsl

import csc.makrobot.api.MakroBot
import csc.makrobot.api.Schedule

@DslMarker
@Target(AnnotationTarget.CLASS)
annotation class MakroBotDsl

@MakroBotDsl
class MakroBotScenario {
    private val actions = arrayListOf<() -> Unit>()
    internal var schedule: Schedule? = null

    infix fun MakroBot.forward(steps: Int) =
        this@MakroBotScenario.actions.add { stepForward(steps) }

    infix fun MakroBot.back(steps: Int) =
        this@MakroBotScenario.actions.add { stepBack(steps) }

    fun MakroBot.turn() = this@MakroBotScenario.actions.add { turnAround() }

    @MakroBotDsl
    class PronounceBlock {
        private val strings = arrayListOf<String>()
        val text: String
            get() = strings.joinToString(separator = System.lineSeparator())

        operator fun String.unaryPlus() = strings.add(this)
    }

    infix fun MakroBot.pronounce(text: PronounceBlock.() -> Unit) {
        val pronounceBlock = PronounceBlock().apply(text)
        this@MakroBotScenario.actions.add { pronounce(pronounceBlock.text) }
    }

    operator fun MakroBot.invoke(settings: MakroBot.() -> Unit) = this.settings()
}

fun scenario(operations: MakroBotScenario.() -> Unit) = MakroBotScenario().apply(operations)

operator fun MakroBot.component1() = name
operator fun MakroBot.component2() = speed
operator fun MakroBot.component3() = power

