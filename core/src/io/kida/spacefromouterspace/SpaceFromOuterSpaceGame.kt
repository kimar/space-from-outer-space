package io.kida.spacefromouterspace

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.kida.spacefromouterspace.model.Cannon

class SpaceFromOuterSpaceGame : ApplicationAdapter() {
    internal var batch: SpriteBatch? = null

    // textures
    internal var planet: Texture? = null
    internal var cannon: Cannon? = null

    override fun create() {
        batch = SpriteBatch()

        // textures
        planet = Texture("Planet14.png")

        // model
        cannon = Cannon()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch?.begin()

        // render (allthethings)
        renderPlanet()

        // rotate cannon
        cannon?.arm(batch)

        // end spritebatch
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
