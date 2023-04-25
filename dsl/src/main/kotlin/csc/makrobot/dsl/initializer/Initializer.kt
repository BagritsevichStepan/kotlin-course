package csc.makrobot.dsl.initializer

interface Initializer<T> {
    fun createInstance(): T
}

fun <T, R : Initializer<T>> createAndInitialize(initializerFactory: () -> R, initialize: R.() -> Unit): T =
    initializerFactory().apply(initialize).createInstance()
