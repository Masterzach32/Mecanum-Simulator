package net.masterzach32.simulator.frame

import net.masterzach32.simulator.frame.ConfigPanel
import net.masterzach32.simulator.frame.SimPanel
import java.awt.*
import javax.swing.*

class Window : JFrame("Mecanum Simulator") {

    val configPanel: ConfigPanel = ConfigPanel()
    val simPanel: SimPanel = SimPanel()

    init {
        layout = FlowLayout()
        add(configPanel)
        add(simPanel)
        pack()
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        val dim = Toolkit.getDefaultToolkit().screenSize
        setLocation(dim.width / 2 - this.size.width / 2, dim.height / 2 - this.size.height / 2)
        isVisible = true
    }
}