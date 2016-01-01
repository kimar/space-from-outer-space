package io.kida.spacefromouterspace

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class SpaceFromOuterSpaceGame : ApplicationAdapter() {
    internal var batch: SpriteBatch? = null
    internal var planet: Texture? = null

    override fun create() {
        batch = SpriteBatch()
        planet = Texture("Planet14.png")
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch?.begin()

        // render (allthethings)
        renderPlanet()

        // end spirebatch
        batch?.end()
    }

    fun renderPlanet() {
        batch?.draw(
            planet,
            Gdx.graphics.width / 2.0f - (planet as Texture).width / 2.0f,
            Gdx.graphics.height / 4.0f - (planet as Texture).height / 1.0f
        )

    }
}
