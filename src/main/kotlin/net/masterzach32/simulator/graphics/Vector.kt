package net.masterzach32.simulator.graphics

import java.awt.Color
import java.awt.Graphics2D
import java.awt.geom.Path2D
import java.awt.geom.AffineTransform
import java.awt.Rectangle

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
        g.color = color
        val r = Rectangle(x, y, thickness, (magnitude*40).toInt())
        val path = Path2D.Double()
        path.append(r, false)
        val t = AffineTransform()
        t.rotate(Math.toRadians(angle))
        path.transform(t)
        g.draw(path)
        g.fill(path)
        g.color = temp
    }
}