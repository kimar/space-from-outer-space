package io.kida.spacefromouterspace.helper

/**
 * Created by kida on 3/01/2016.
 */

class MathHelper {
    fun toDegree(angle: Double): Double {
        return (angle + 90) * Math.PI / 180
    }
}