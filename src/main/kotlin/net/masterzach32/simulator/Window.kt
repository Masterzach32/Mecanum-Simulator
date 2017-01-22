package net.masterzach32.simulator

import java.awt.*
import java.util.*
import javax.swing.*

class Window : JFrame("Mechanum Simulator") {

    init {
        layout = FlowLayout()
        add(ConfigPanel())
        add(SimPanel())
        pack()
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        val dim = Toolkit.getDefaultToolkit().screenSize
        setLocation(dim.width / 2 - this.size.width / 2, dim.height / 2 - this.size.height / 2)
        isVisible = true
    }

    class ConfigPanel : JPanel() {
        val labels = ArrayList<JLabel>()
        val sliders = ArrayList<JSlider>()
        val values = ArrayList<JLabel>()

        init {
            layout = GridLayout(4, 3, 10, 10)

            labels.add(JLabel("Left Pitch"))
            labels.add(JLabel("Left Roll"))
            labels.add(JLabel("Right Pitch"))
            labels.add(JLabel("Right Roll"))

            for (i in labels.indices) {
                sliders.add(JSlider(-100, 100, 0))
                sliders[i].preferredSize = Dimension(40, 150)
                sliders[i].majorTickSpacing = 10
                sliders[i].minorTickSpacing = 5
                sliders[i].paintTicks = true
                sliders[i].snapToTicks = true

                values.add(JLabel("0.0"))

                add(labels[i])
                add(sliders[i])
                add(values[i])

                sliders[i].addChangeListener { values[i].text = "${sliders[i].value.toDouble()/100}" }
            }
        }

        override fun getPreferredSize() = Dimension(300, 300)
    }

    class SimPanel : JPanel() {
        init {

        }

        override fun getPreferredSize() = Dimension(300, 300)

        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)

            val startX = 40
            val startY = 50
            val botWidth = 180
            val botHeight = 200

            g.color = Color.RED
            // bounding box
            //g.fillRect(startX, startY, botWidth, botHeight)

            // wheels
            val wheelWidth = 20
            val wheelHeight = 40
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
            val metalWidth = 15
            val sideLength = botHeight
            val overlap = 20
            val topLength = overlap + wheelWidth + metalWidth
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

        }
    }
}

fun main(args: Array<String>) {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    Window()
}