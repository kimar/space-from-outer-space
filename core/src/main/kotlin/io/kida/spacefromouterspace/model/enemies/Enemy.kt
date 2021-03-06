package io.kida.spacefromouterspace.model.enemies

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Rectangle
import io.kida.spacefromouterspace.helper.MathHelper
import io.kida.spacefromouterspace.model.Projectile
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by kida on 3/01/2016.
 */

open class Enemy(spriteBatch: SpriteBatch, enemies: CopyOnWriteArrayList<Enemy>, texture: Texture, speed: Double) {

    // internal vals
    internal val texture = texture
    internal val speed = speed
    internal val enemies = enemies
    internal var spriteBatch = spriteBatch

    // internal vars
    internal var sprite: Sprite? = null

    // private vars
    private var xCoord = 0.0
    private var yCoord = 0.0
    private var vSpeed: Double? = null
    private var hSpeed: Double? = null
    private var victoryIsMine = 0.0

    fun approach(fromX: Double, fromY: Double, toX: Double, toY: Double) {

        victoryIsMine = Gdx.graphics.height.toDouble()
        xCoord = fromX - toX
        yCoord = fromY - toY

        val angle = Math.atan2(
                toX - fromX,
                toY - fromY
        ) * 180.0 / Math.PI

        vSpeed = Math.sin(MathHelper().toDegree(angle)) * speed
        hSpeed = Math.cos(MathHelper().toDegree(angle)) * speed

        sprite = Sprite(texture, 0, 0, texture.width, texture.height)
        sprite!!.rotate(angle.toFloat() - 180.0f)

        enemies.add(this)
    }

    fun render() {
        xCoord += hSpeed!! * Gdx.graphics.deltaTime
        yCoord += vSpeed!! * Gdx.graphics.deltaTime
        victoryIsMine += vSpeed!! * Gdx.graphics.deltaTime

        sprite?.setPosition(xCoord.toFloat(), yCoord.toFloat())
        sprite?.draw(spriteBatch)
    }

    fun kill() {
        enemies.remove(this)
    }

    fun hasSurvived(): Boolean {
        return victoryIsMine <= 0
    }

    fun projectileHit(projectiles: CopyOnWriteArrayList<Projectile>): Projectile? {
        var projectile:Projectile? = null
        projectiles.forEach {
            if (sprite!!.boundingRectangle.overlaps(it.boundingRectangle())) {
                projectile = it
                return@forEach
            }
        }
        return projectile
    }

}