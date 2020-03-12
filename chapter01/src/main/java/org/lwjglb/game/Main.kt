package org.lwjglb.game

import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryUtil

class Main {

    // The window handle
    private var window: Long = 0
    private val windowWidth = 300
    private val windowsHeight = 300

    fun run() {
        println("Hello LWJGL " + Version.getVersion() + "!")
        try {
            init()
            loop()

            // Release window and window callbacks
            Callbacks.glfwFreeCallbacks(window)
            GLFW.glfwDestroyWindow(window)
        } finally {
            // Terminate GLFW and release the GLFWerrorfun
            GLFW.glfwTerminate()
            GLFW.glfwSetErrorCallback(null) !!.free()
        }
    }

    private fun init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set()

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        check(GLFW.glfwInit()) { "Unable to initialize GLFW" }

        // Configure our window
        GLFW.glfwDefaultWindowHints() // optional, the current window hints are already the default
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE) // the window will stay hidden after creation
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE) // the window will be resizable

        // Create the window
        window = GLFW.glfwCreateWindow(windowWidth, windowsHeight, "Hello World!", MemoryUtil.NULL, MemoryUtil.NULL)
        if (window == MemoryUtil.NULL) {
            throw RuntimeException("Failed to create the GLFW window")
        }

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        GLFW.glfwSetKeyCallback(window) { window: Long, key: Int, _: Int, action: Int, _: Int ->
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
                GLFW.glfwSetWindowShouldClose(
                    window,
                    true
                ) // We will detect this in the rendering loop
            }
        }

        // Get the resolution of the primary monitor
        val videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor()) ?: throw NullPointerException()
        // Center our window
        GLFW.glfwSetWindowPos(
            window,
            (videoMode.width() - windowWidth) / 2,
            (videoMode.height() - windowsHeight) / 2
        )

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(window)
        // Enable v-sync
        GLFW.glfwSwapInterval(1)

        // Make the window visible
        GLFW.glfwShowWindow(window)
    }

    private fun loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities()

        // Set the clear color
        GL11.glClearColor(1.0f, 0.0f, 0.0f, 0.0f)

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (! GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT) // clear the framebuffer
            GLFW.glfwSwapBuffers(window) // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            GLFW.glfwPollEvents()
        }
    }

}

fun main() {
    Main().run()
}