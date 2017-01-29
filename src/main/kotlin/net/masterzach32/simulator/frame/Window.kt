package net.masterzach32.simulator.frame

import com.opencsv.CSVReader
import net.masterzach32.simulator.LOGGER
import java.awt.*
import java.io.FileReader
import java.util.*
import javax.swing.*

class Window : JFrame("Mecanum Simulator") {

    val configPanel: ConfigPanel = ConfigPanel()
    val simPanel: SimPanel = SimPanel()

    val menuBar = JMenuBar()
    val fileMenu = JMenu("File")
    val helpMenu = JMenu("Help")
    val importPath = JMenuItem("Import Path (.csv)")
    val exportGif = JMenuItem("Export GIF")
    val exitMenu = JMenuItem("Exit")

    val about = JMenuItem("About")
    val aboutFrame = AboutFrame(this)
    val checkForUpdates = JMenuItem("Check for Updates")

    init {
        layout = BorderLayout()
        fileMenu.add(importPath)
        importPath.addActionListener {
            val chooser = JFileChooser()
            chooser.showOpenDialog(this)
            if (chooser.selectedFile == null)
                return@addActionListener
            val reader = CSVReader(FileReader(chooser.selectedFile))
            simPanel.enableCSVMode(parseSpeedFromCSV(reader), parseMSFromCSV(reader))
            configPanel.enableCSVMode()
            exportGif.isEnabled = true
        }
        fileMenu.add(exportGif)
        exportGif.isEnabled = false
        fileMenu.addSeparator()
        fileMenu.add(exitMenu)
        exitMenu.addActionListener { System.exit(0) }
        menuBar.add(fileMenu)
        helpMenu.add(checkForUpdates)
        helpMenu.addSeparator()
        about.addActionListener {
            aboutFrame.isVisible = true
            aboutFrame.repaint()
        }
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

    fun parseSpeedFromCSV(reader: CSVReader): List<Double> {
        val list = ArrayList<Double>()
        reader.forEach {
            list.add(it[1].toDouble())
            LOGGER.info(it[1])
        }
        return list
    }

    fun parseMSFromCSV(reader: CSVReader): Int {
        return 20 //reader.readNext()[2].toInt() TODO fix
    }
}