package io.kida.spacefromouterspace.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import io.kida.spacefromouterspace.SpaceFromOuterSpaceGame

object DesktopLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        // val config = JglfwApplicationConfiguration()
        val config = LwjglApplicationConfiguration();
        config.width = 640
        config.height = 480
        config.vSyncEnabled = true
        // config.useHDPI = true;
        // config.undecorated = true;
        LwjglApplication(SpaceFromOuterSpaceGame(), config)
    }
}