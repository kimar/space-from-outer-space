package io.kida.spacefromouterspace

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.TimeUtils
import io.kida.spacefromouterspace.helper.MathHelper
import io.kida.spacefromouterspace.model.Cannon
import io.kida.spacefromouterspace.model.Projectile
import io.kida.spacefromouterspace.model.enemies.Enemy
import io.kida.spacefromouterspace.model.enemies.Helmut
import java.util.*

class SpaceFromOuterSpaceGame : ApplicationAdapter() {

    internal var batch: SpriteBatch? = null
    internal var lastEnemyAdded: Long = 0

    // textures
    internal var planet: Texture? = null
    internal var cannon: Cannon? = null

    internal var projectiles: ArrayList<Projectile>? = null
    internal var enemies: ArrayList<Enemy>? = null

    override fun create() {

        projectiles = ArrayList<Projectile>()
        enemies = ArrayList<Enemy>()

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
        cannon?.arm(batch, projectiles!!)

        // render projectiles
        projectiles?.forEach {
            it.render()
        }

        // render existing enemies
        enemies?.forEach {
            it.render()
        }

        // add random enemy
        if(shouldAddEnemy()) {
            Helmut(batch!!, enemies!!)
                    .approach(
                            Gdx.graphics.width/2.0,
                            Gdx.graphics.height.toDouble(),
                            -180.0
                    )
            lastEnemyAdded = TimeUtils.millis()
        }

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

    fun shouldAddEnemy(): Boolean {
        if (TimeUtils.millis() >= lastEnemyAdded + 1000) {
            return Math.random() * 10 > 5
        }
        return false
    }

}
