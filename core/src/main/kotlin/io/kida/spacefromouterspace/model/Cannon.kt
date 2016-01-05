package io.kida.spacefromouterspace.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.TimeUtils
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by kida on 2/01/2016.
 */

class Cannon {
    private enum class Direction {
        Left, Right
    }

    // cannon position
    private var cannonX: Double? = null
    private var cannonY: Double? = null

    // private vars
    private var texture: Texture = Texture("cannon.png")
    private var currentAngle = 90f
    private var lastPeakPoint = Direction.Right
    private var spriteBatch: SpriteBatch? = null
    private var projectiles: CopyOnWriteArrayList<Projectile>? = null
    private var cannonSprite: Sprite? = null

    // lock fire button
    private var projectileFired = false
    private var lastFireTime: Long = 0
    private val fireTimeThreshold = 200

    private var mPlayer: Player? = null
    private var mSounds: Sounds? = null

    private fun rotateDirection(direction: Direction) {
        if (direction == Direction.Right) {
            currentAngle -= 100 * Gdx.graphics.deltaTime
        } else {
            currentAngle += 100 * Gdx.graphics.deltaTime
        }
    }

    public fun arm(batch: SpriteBatch?, player: Player, sounds: Sounds, projectile: CopyOnWriteArrayList<Projectile>) {
        spriteBatch = batch
        projectiles = projectile
        mPlayer = player
        mSounds = sounds

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
        cannonSprite = Sprite(texture, 0, 0, texture.width, texture.height)

        // set origin bo lower part of cannon
        cannonSprite!!.setOrigin(texture.width / 2.0f, (texture.height / 10.0f) * -1)

        // position cannon in lower middle part of window
        cannonX = Gdx.graphics.width / 2.0 - texture.width / 2.0
        cannonY = Gdx.graphics.height / 4.0 - texture.height
        cannonSprite!!.setPosition(cannonX!!.toFloat(), cannonY!!.toFloat())

        // rotate cannon given to the rotation vector
        cannonSprite!!.rotate(currentAngle)
        cannonSprite!!.draw(spriteBatch)

        // unlock fire button?
        if (TimeUtils.millis() >= lastFireTime + fireTimeThreshold) {
            projectileFired = false
        }

        // fire?
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (!projectileFired) {
                fire(currentAngle)
                lastFireTime = TimeUtils.millis()
            }
            projectileFired = true
        }
    }

    private fun fire(angle: Float) {
        if (spriteBatch == null) {
            Gdx.app.log("Cannon", "Could'n fire cannon due to invalid SpriteBatch")
            return
        }
        Projectile(
                cannonSprite!!.vertices[SpriteBatch.X2].toDouble(),
                cannonSprite!!.vertices[SpriteBatch.Y2].toDouble(),
                spriteBatch!!,
                projectiles!!
        ).shoot(angle.toDouble())
        mPlayer!!.decreaseAmmo()
        mSounds!!.missile?.play()
    }

}