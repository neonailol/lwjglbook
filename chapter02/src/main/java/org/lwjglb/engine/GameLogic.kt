package org.lwjglb.engine

interface GameLogic {
    fun init()
    fun input(window: Window)
    fun update(interval: Float)
    fun render(window: Window)
}
