package net.masterzach32.simulator

import java.awt.Color
import java.awt.Graphics2D

/**
 * @param text Text to be displayed next to the arrow
 * @param magnitude The magnitude of the vector between 0-1
 * @param angle The angle of the vector
 * @param thickness The thickness of the arrow
 */
class Vector(var text: String, val x: Int, val y: Int, var magnitude: Double, var angle: Double, val thickness: Int, val color: Color) {

    val rads = Math.toRadians(angle)

    fun render(g: Graphics2D) {
        if (magnitude == 0.0)
            return
        val temp = g.color
        g.rotate(rads)
        g.color = color
        val length = (magnitude*40).toInt()
        if (magnitude < 0)
            g.fillRect(x - thickness/2, y, thickness, -length)
        else
            g.fillRect(x - thickness/2, y - length, thickness, length)
        g.rotate(rads)
        g.color = temp
    }
}