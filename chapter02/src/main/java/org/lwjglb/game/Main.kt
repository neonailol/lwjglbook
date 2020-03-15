package org.lwjglb.game

import org.lwjglb.engine.GameEngine
import org.lwjglb.engine.GameLogic
import kotlin.system.exitProcess

fun main() {
    try {
        val vSync = true
        val gameLogic: GameLogic = DummyGame()
        val gameEng = GameEngine("GAME", 600, 480, vSync, gameLogic)
        gameEng.run()
    } catch (exception: Exception) {
        exception.printStackTrace()
        exitProcess(- 1)
    }
}
