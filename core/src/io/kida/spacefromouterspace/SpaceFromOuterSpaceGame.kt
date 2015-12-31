package io.kida.spacefromouterspace

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class SpaceFromOuterSpaceGame : ApplicationAdapter() {
    internal var batch: SpriteBatch? = null
    internal var img: Texture? = null

    internal var movX = 0f
    internal var movY = 0f
    internal var movSpeed = 100f
    override fun create() {
        batch = SpriteBatch();
        img = Texture("badlogic.jpg")
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch?.begin()

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            movX -= Gdx.graphics.getDeltaTime() * movSpeed
        } else if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            movX += Gdx.graphics.getDeltaTime() * movSpeed
        } else if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            movY += Gdx.graphics.getDeltaTime() * movSpeed
        } else if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
            movY -= Gdx.graphics.getDeltaTime() * movSpeed
        }
//        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
//            batch?.draw(img, 100f, 0f)
//        } else {
//            batch?.draw(img, 0f, 0f)
//        }
        batch?.draw(img, movX, movY)
        batch?.end()
    }
}
