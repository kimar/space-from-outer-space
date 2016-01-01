package io.kida.spacefromouterspace.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * Created by kida on 2/01/2016.
 */

class Cannon {
    private enum class Direction {
        Left, Right
    }

    // private vars
    private var texture: Texture = Texture("cannon.png")
    private var currentAngle = 90f
    private var lastPeakPoint = Direction.Right

    private fun rotateDirection(direction: Direction) {
        if (direction == Direction.Right) {
            currentAngle -= 100 * Gdx.graphics.deltaTime
        } else {
            currentAngle += 100 * Gdx.graphics.deltaTime
        }
    }

    public fun arm(batch: SpriteBatch?) {

        // rotate cannon
        if (currentAngle > 90f && lastPeakPoint == Direction.Right) {
            lastPeakPoint = Direction.Left
            currentAngle = 90f
        } else if (currentAngle < -90f && lastPeakPoint == Direction.Left) {
            lastPeakPoint = Direction.Right
            currentAngle = -90f
        }

        if (lastPeakPoint == Direction.Left) {
            rotateDirection(Direction.Right)
        } else if (lastPeakPoint == Direction.Right) {
            rotateDirection(Direction.Left)
        }

        // render rotated cannon
        val sprite = Sprite(texture, 0, 0, 37, 130)

        // set origin bo lower part of cannon
        sprite.setOrigin(texture.width / 2.0f, (texture.height / 10.0f) * -1)

        // position cannon in lower middle part of window
        sprite.setPosition(Gdx.graphics.width / 2.0f - texture.width / 2.0f,
                Gdx.graphics.height / 4.0f - texture.height)

        // rotate cannon given to the rotation vector
        sprite.rotate(currentAngle)
        sprite.draw(batch)
    }

}