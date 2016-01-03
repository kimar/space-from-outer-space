package io.kida.spacefromouterspace.model.enemies

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.kida.spacefromouterspace.helper.MathHelper
import java.util.*

/**
 * Created by kida on 3/01/2016.
 */

open class Enemy(spriteBatch: SpriteBatch, enemies: ArrayList<Enemy>, texture: Texture, speed: Double) {

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

    fun approach(x: Double, y: Double, angle: Double) {

        xCoord = x
        yCoord = y

        vSpeed = Math.sin(MathHelper().toDegree(angle)) * speed
        hSpeed = Math.cos(MathHelper().toDegree(angle)) * speed

        sprite = Sprite(texture, 0, 0, texture.width, texture.height)
        sprite!!.rotate(angle.toFloat())

        enemies.add(this)
    }

    fun render() {
        xCoord += hSpeed!! * Gdx.graphics.deltaTime
        yCoord += vSpeed!! * Gdx.graphics.deltaTime

        sprite?.setPosition(xCoord.toFloat(), yCoord.toFloat())
        sprite?.draw(spriteBatch)
    }

}