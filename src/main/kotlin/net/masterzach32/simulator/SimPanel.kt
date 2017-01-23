package net.masterzach32.simulator

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.util.*
import javax.swing.JPanel

class SimPanel : JPanel() {

    val arrows = ArrayList<Vector>()

    val startX = 50
    val startY = 50
    val botWidth = 180
    val botHeight = 200
    val wheelWidth = 20
    val wheelHeight = 40
    val metalWidth = 15
    val sideLength = botHeight
    val overlap = 20
    val topLength = overlap + wheelWidth + metalWidth

    init {
        arrows.add(Vector("Left Front", startX + wheelWidth/2, startY + wheelHeight/2, 0.0, 0.0, 5, Color.GREEN))
        arrows.add(Vector("Right Front", startX + botWidth - wheelWidth/2, startY + wheelHeight/2, 0.0, 0.0, 5, Color.GREEN))
        arrows.add(Vector("Left Rear", startX + wheelWidth/2, startY + botHeight - wheelHeight/2, 0.0, 0.0, 5, Color.GREEN))
        arrows.add(Vector("Right Rear", startX + botWidth - wheelWidth/2, startY + botHeight - wheelHeight/2, 0.0, 0.0, 5, Color.GREEN))
    }

    override fun getPreferredSize() = Dimension(300, 300)

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        g.color = Color.RED
        // bounding box
        //g.fillRect(startX, startY, botWidth, botHeight)

        // wheels
        g.color = Color.BLACK
        // top left
        g.fillRect(startX, startY, wheelWidth, wheelHeight)
        // top right
        g.fillRect(startX + botWidth - wheelWidth, startY, wheelWidth, wheelHeight)
        // bottom left
        g.fillRect(startX, startY + botHeight - wheelHeight, wheelWidth, wheelHeight)
        // bottom right
        g.fillRect(startX + botWidth - wheelWidth, startY + botHeight - wheelHeight, wheelWidth, wheelHeight)

        // chassis
        g.color = Color.GRAY
        // left side
        g.fillRect(startX + wheelWidth, startY, metalWidth, sideLength)
        // right side
        g.fillRect(startX + botWidth - wheelWidth - metalWidth, startY, metalWidth, sideLength)
        // bottom
        g.fillRect(startX - overlap, startY + botHeight, botWidth + overlap*2, metalWidth)
        // middle
        g.fillRect(startX + wheelWidth + metalWidth, startY + wheelHeight, botWidth - wheelWidth*2 - metalWidth*2, metalWidth)
        // top left
        g.fillRect(startX - overlap, startY - metalWidth, topLength, metalWidth)
        // top right
        g.fillRect(startX + botWidth - topLength + overlap, startY - metalWidth, topLength, metalWidth)

        arrows.forEach { it.render(g as Graphics2D) }
    }
}