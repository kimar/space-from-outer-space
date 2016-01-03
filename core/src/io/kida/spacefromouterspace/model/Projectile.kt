
package io.kida.spacefromouterspace.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.kida.spacefromouterspace.helper.MathHelper
import java.util.*

/**
 * Created by kida on 2/01/2016.
 */

class Projectile(x: Double, y: Double, batch: SpriteBatch, projectile: ArrayList<Projectile>) {

    // private movement vars
    private var x: Double = x
    private var y: Double = y
    private var vSpeed: Double? = null
    private var hSpeed: Double? = null
    private var speed = 100.0

    // general private vars
    private var texture: Texture = Texture("projectile.png")
    private var spriteBatch = batch
    private var projectiles = projectile
    private var sprite: Sprite? = null

    fun shoot(angle: Double) {
        vSpeed = Math.sin(MathHelper().toDegree(angle)) * speed
        hSpeed = Math.cos(MathHelper().toDegree(angle)) * speed

        sprite = Sprite(texture, 0, 0, 30, 39)
        sprite!!.rotate(angle.toFloat())
        projectiles.add(this)
    }

    fun render() {
        x += hSpeed!! * Gdx.graphics.deltaTime
        y += vSpeed!! * Gdx.graphics.deltaTime

        sprite?.setPosition(x.toFloat(), y.toFloat())
        sprite?.draw(spriteBatch)
    }
}