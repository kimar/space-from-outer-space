package io.kida.spacefromouterspace

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.TimeUtils
import io.kida.spacefromouterspace.model.Cannon
import io.kida.spacefromouterspace.model.Player
import io.kida.spacefromouterspace.model.Projectile
import io.kida.spacefromouterspace.model.Sounds
import io.kida.spacefromouterspace.model.enemies.Enemy
import io.kida.spacefromouterspace.model.enemies.Helmut
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.collections.count

class SpaceFromOuterSpaceGame : ApplicationAdapter() {

    internal var batch: SpriteBatch? = null
    internal var lastEnemyAdded: Long = 0

    // textures
    internal var planet: Texture? = null
    internal var cannon: Cannon? = null

    internal var projectiles: CopyOnWriteArrayList<Projectile>? = null
    internal var enemies: CopyOnWriteArrayList<Enemy>? = null

    internal var player = Player()
    internal var sounds = Sounds()

    override fun create() {

        projectiles = CopyOnWriteArrayList<Projectile>()
        enemies = CopyOnWriteArrayList<Enemy>()
        sounds.load()

        batch = SpriteBatch()

        // textures
        planet = Texture("Planet14.png")

        // model
        cannon = Cannon()
    }

    override fun dispose() {
        sounds.unload()
        super.dispose()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch?.begin()

        // render (allthethings)
        renderPlanet()

        // rotate cannon
        cannon?.arm(batch, player, sounds, projectiles!!)

        // render projectiles
        projectiles?.forEach {
            it.render()
        }

        // render existing enemies
        // perform hit test
        // check if enemy has made it through
        enemies?.forEach {
            it.render()
            val hitProjectile = it.projectileHit(projectiles!!)
            if (hitProjectile != null) {
                it.kill()
                hitProjectile.explode()
                sounds.explosion?.play()
                player.increaseScore()
            }
            if (it.hasSurvived()) {
                player.decreaseLife()
                sounds.alien?.play()
                it.kill()
            }
        }

        // add random enemy
        val shouldAddEnemy = shouldAddEnemy()
        if(shouldAddEnemy.yes) {
//        if (enemies?.count() == 0 && shouldAddEnemy.yes) {
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

        // render labels
        renderAmmo()
        renderLife()
        renderScore()

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

    fun getCommonFont(): BitmapFont {
        val font = BitmapFont()
        font.color = Color.WHITE
        return font
    }

    fun renderAmmo() {
        getCommonFont().draw(
                batch,
                "Ammo: ${player.getAmmo()}",
                10.0f,
                Gdx.graphics.height.toFloat() - 10
        )
    }

    fun renderLife() {
        getCommonFont().draw(
                batch,
                "Life: ${player.getLife()}",
                Gdx.graphics.width.toFloat() - 100,
                Gdx.graphics.height.toFloat() - 10
        )
    }

    fun renderScore() {
        getCommonFont().draw(
                batch,
                "Score: ${player.getScore()}",
                Gdx.graphics.width / 2.0f - 50,
                Gdx.graphics.height.toFloat() - 10
        )
    }

}
