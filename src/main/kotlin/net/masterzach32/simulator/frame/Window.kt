package net.masterzach32.simulator.frame

import java.awt.*
import javax.swing.*

class Window : JFrame("Mecanum Simulator") {

    val configPanel: ConfigPanel = ConfigPanel()
    val simPanel: SimPanel = SimPanel()
    val menuBar = JMenuBar()
    val fileMenu = JMenu("File")
    val helpMenu = JMenu("Help")
    val importPath = JMenuItem("Import Path")
    val exportGif = JMenuItem("Export GIF")

    val about = JMenuItem("About")
    val aboutFrame = AboutFrame()
    val checkForUpdates = JMenuItem("Check for Updates")

    init {
        layout = BorderLayout()
        fileMenu.add(importPath)
        fileMenu.add(exportGif)
        menuBar.add(fileMenu)
        helpMenu.add(checkForUpdates)
        helpMenu.addSeparator()
        about.addActionListener { aboutFrame.isVisible = true }
        helpMenu.add(about)
        menuBar.add(helpMenu)
        add(menuBar, BorderLayout.NORTH)
        val panel = JPanel(FlowLayout())
        panel.add(configPanel)
        panel.add(simPanel)
        add(panel, BorderLayout.CENTER)
        pack()
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        minimumSize = size
        val dim = Toolkit.getDefaultToolkit().screenSize
        setLocation(dim.width / 2 - size.width / 2, dim.height / 2 - size.height / 2)
        isVisible = true
    }
}