package io.kida.spacefromouterspace.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound

/**
 * Created by kida on 5/01/2016.
 */

class Sounds {
    var missile: Sound? = null
    var explosion: Sound? = null
    var alien: Sound? = null

    fun load() {
        missile = Gdx.audio.newSound(Gdx.files.internal("sounds/missile.wav"))
        explosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"))
        alien = Gdx.audio.newSound(Gdx.files.internal("sounds/alien.wav"))
    }

    fun unload() {
        missile = null
        explosion = null
        alien = null
    }
}