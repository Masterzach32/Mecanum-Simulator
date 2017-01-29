package net.masterzach32.simulator.frame

import net.masterzach32.simulator.graphics.Vector
import org.apache.commons.csv.CSVRecord
import java.awt.*
import java.util.*
import javax.swing.JPanel
import javax.swing.JSlider

class SimPanel : JPanel() {

    val arrows = ArrayList<Vector>()

    val slider = JSlider()

    private var csvPath: List<Double>? = null
    private var max = 0

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
        layout = BorderLayout()
        add(slider, BorderLayout.SOUTH)
        slider.minimum = 0
        slider.maximum = 10
        slider.majorTickSpacing = 5
        slider.minorTickSpacing = 1
        slider.value = 0
        slider.paintTicks = true
        slider.snapToTicks = true
        slider.isEnabled = false
        slider.addChangeListener {
            arrows.forEach { it.magnitude = (csvPath as List<Double>)[slider.value]}
        }
        arrows.add(Vector("Left Front", startX + wheelWidth/2, startY + wheelHeight/2, 0.0, 0.0, 5, Color.GREEN))
        arrows.add(Vector("Right Front", startX + botWidth - wheelWidth/2, startY + wheelHeight/2, 0.0, 0.0, 5, Color.GREEN))
        arrows.add(Vector("Left Rear", startX + wheelWidth/2, startY + botHeight - wheelHeight/2, 0.0, 0.0, 5, Color.GREEN))
        arrows.add(Vector("Right Rear", startX + botWidth - wheelWidth/2, startY + botHeight - wheelHeight/2, 0.0, 0.0, 5, Color.GREEN))
    }

    override fun getPreferredSize() = Dimension(300, 400)

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

    fun enableCSVMode(speeds: List<Double>, ms: Int) {
        slider.isEnabled = true
        slider.maximum = speeds.size
        csvPath = speeds
    }

    fun disableCSVMode() {
        if (csvPath == null)
            return
        slider.maximum = 10
        slider.value = 0
        slider.isEnabled = false
    }
}