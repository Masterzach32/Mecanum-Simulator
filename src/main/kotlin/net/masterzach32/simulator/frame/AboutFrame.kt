package net.masterzach32.simulator.frame

import net.masterzach32.simulator.CONFIG
import java.awt.*
import java.net.URI
import javax.swing.*
import java.awt.Desktop

class AboutFrame(frame: JFrame) : JDialog(frame) {

    val title = JLabel("Mecanum Simulator")
    val team = JLabel("${CONFIG.getString("frc-team.name")} (${CONFIG.getString("frc-team.num")})")
    val link = URI(CONFIG.getString("frc-team.url"))
    val website = JButton()

    init {
        layout = FlowLayout()
        defaultCloseOperation = WindowConstants.HIDE_ON_CLOSE
        isModal = true
        val panel = JPanel()
        panel.layout = GridLayout(1, 2)
        panel.add(JLabel(ImageIcon(javaClass.classLoader.getResource("logo.png"))))

        val titlePanel = JPanel()
        titlePanel.layout = GridLayout(3, 1)
        title.horizontalAlignment = JLabel.CENTER
        titlePanel.add(title)
        team.horizontalAlignment = JLabel.CENTER
        titlePanel.add(team)
        website.text = "<HTML><FONT color=\"#000099\"><U>Website</U></FONT></HTML>"
        website.horizontalAlignment = JButton.CENTER
        website.isBorderPainted = false
        website.isOpaque = false
        website.background = Color.WHITE
        website.toolTipText = link.toString()
        website.cursor = Cursor(Cursor.HAND_CURSOR)
        website.addActionListener {
            Desktop.getDesktop().browse(link)
            isVisible = false
        }
        titlePanel.add(website)

        panel.add(titlePanel)

        add(panel)
        isAlwaysOnTop = true
        pack()
        isResizable = false
        val dim = Toolkit.getDefaultToolkit().screenSize
        setLocation(dim.width / 2 - this.size.width / 2, dim.height / 2 - this.size.height / 2)
    }
}