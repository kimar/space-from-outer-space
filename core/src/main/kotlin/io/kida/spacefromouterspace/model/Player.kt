package io.kida.spacefromouterspace.model

import com.badlogic.gdx.Gdx

/**
 * Created by kida on 5/01/2016.
 */

class Player {
    private var ammo = 100
    private var life = 10
    private var score = 0

    fun decreaseAmmo(): Int {
        ammo--
        Gdx.app.log("Player", "new ammunition: ${ammo}")
        return ammo
    }

    fun getAmmo(): Int {
        return ammo
    }

    fun decreaseLife(): Int {
        life--
        Gdx.app.log("Player", "new life: ${life}")
        return life
    }

    fun getLife(): Int {
        return life
    }

    fun increaseScore(): Int {
        score++
        Gdx.app.log("Player", "new score: ${life}")
        return score
    }

    fun getScore(): Int {
        return score
    }
}