package net.masterzach32.simulator

import java.awt.*
import java.util.*
import javax.swing.*

class Window : JFrame("Mechanum Simulator") {

    val configPanel: ConfigPanel = ConfigPanel()
    val simPanel: SimPanel = SimPanel()

    init {
        layout = FlowLayout()
        add(configPanel)
        add(simPanel)
        pack()
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        val dim = Toolkit.getDefaultToolkit().screenSize
        setLocation(dim.width / 2 - this.size.width / 2, dim.height / 2 - this.size.height / 2)
        isVisible = true
    }
}