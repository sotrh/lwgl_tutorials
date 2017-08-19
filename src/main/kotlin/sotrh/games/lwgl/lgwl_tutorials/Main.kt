package sotrh.games.lwgl.lgwl_tutorials

import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.opengl.GL

/**
 * Created by benjamin on 8/18/17
 */

object Main {

    val NULL = 0L

    private lateinit var errorCallback: GLFWErrorCallback
    private lateinit var keyCallback: GLFWKeyCallback
    private var window: Long = NULL

    @JvmStatic fun main(args: Array<String>) {
        // create the error callback
        errorCallback = GLFWErrorCallback.createPrint(System.err)
        glfwSetErrorCallback(errorCallback)

        if (!glfwInit()) throw IllegalStateException("Unable to initialize GLFW")

        window = glfwCreateWindow(640, 480, "LWGL Tutorials", NULL, NULL)
        if (window == NULL) {
            glfwTerminate()
            throw RuntimeException("Failed to create the GLFW window")
        }

        // create the key callback
        keyCallback = object : GLFWKeyCallback() {
            override fun invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
                glfwSetWindowShouldClose(window, true)
            }
        }
        glfwSetKeyCallback(window, keyCallback)

        glfwMakeContextCurrent(window)
        GL.createCapabilities()

        while (!glfwWindowShouldClose(window)) {
            glfwSwapBuffers(window)
            glfwPollEvents()
        }

        glfwDestroyWindow(window)
        keyCallback.free()

        glfwTerminate()
        errorCallback.free()
    }
}
