
package io.kida.spacefromouterspace.model

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.*

/**
 * Created by kida on 2/01/2016.
 */

class Projectile(batch: SpriteBatch, projectile: ArrayList<Projectile>) {

    // private vars
    private var texture: Texture = Texture("projectile.png")
    private var spriteBatch = batch
    private var projectiles = projectile
    private var sprite: Sprite? = null

    fun shoot(angle: Float) {
        sprite = Sprite(texture, 0, 0, 30, 39)
        sprite?.rotate(angle)
        projectiles.add(this)
    }

    fun render() {
        sprite?.draw(spriteBatch)
    }
}