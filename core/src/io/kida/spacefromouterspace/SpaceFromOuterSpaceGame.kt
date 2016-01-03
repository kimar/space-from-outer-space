package io.kida.spacefromouterspace

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.TimeUtils
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
        val shouldAddEnemy = shouldAddEnemy()
        if(shouldAddEnemy.yes) {
            Gdx.app.log("Helmut approach", "fromX: ${shouldAddEnemy.x}, fromY: ${shouldAddEnemy.y}, toX: ${Gdx.graphics.width.toDouble() / 2.0}, toY: ${0.0}")
            Helmut(batch!!, enemies!!)
                    .approach(
                            shouldAddEnemy.x,
                            shouldAddEnemy.y,
                            Gdx.graphics.width.toDouble() / 2.0,
                            0.0
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

    data class ShouldAddEnemyResult(val yes: Boolean, val x: Double, val y: Double)
    fun shouldAddEnemy(): ShouldAddEnemyResult {
        if (TimeUtils.millis() >= lastEnemyAdded + 1000) {
            val randVal = MathUtils.random(0, 100)
            return ShouldAddEnemyResult(
                    randVal > 50,
                    (Gdx.graphics.width.toDouble() / 100) * randVal,
                    Gdx.graphics.height.toDouble()
            )
        }
        return ShouldAddEnemyResult(false, 0.0, 0.0)
    }

}
