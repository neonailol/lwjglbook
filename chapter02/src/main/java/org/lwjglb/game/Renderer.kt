package org.lwjglb.game

import org.lwjgl.opengl.GL11

class Renderer {

    fun init() = Unit

    fun clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
    }

}
