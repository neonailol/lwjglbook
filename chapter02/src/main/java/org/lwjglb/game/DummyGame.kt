package org.lwjglb.game

import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11
import org.lwjglb.engine.GameLogic
import org.lwjglb.engine.Window

class DummyGame : GameLogic {

    private var direction = 0
    private var color = 0.0f
    private val renderer: Renderer = Renderer()

    override fun init() {
        renderer.init()
    }

    override fun input(window: Window) {
        direction = when {
            window.isKeyPressed(GLFW.GLFW_KEY_UP) -> 1
            window.isKeyPressed(GLFW.GLFW_KEY_DOWN) -> - 1
            else -> 0
        }
    }

    override fun update(interval: Float) {
        color += direction * 0.01f
        if (color > 1) {
            color = 1.0f
        } else if (color < 0) {
            color = 0.0f
        }
    }

    override fun render(window: Window) {
        if (window.isResized) {
            GL11.glViewport(0, 0, window.width, window.height)
            window.isResized = false
        }
        window.setClearColor(color, color, color, 0.0f)
        renderer.clear()
    }

}
