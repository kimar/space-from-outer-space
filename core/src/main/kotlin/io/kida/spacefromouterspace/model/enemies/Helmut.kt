package io.kida.spacefromouterspace.model.enemies

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by kida on 3/01/2016.
 */

class Helmut(spriteBatch: SpriteBatch, enemies: CopyOnWriteArrayList<Enemy>):
        Enemy(spriteBatch, enemies, Texture("helmut.png"), 100.0) {

}