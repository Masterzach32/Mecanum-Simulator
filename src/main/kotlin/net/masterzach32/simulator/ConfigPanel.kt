package net.masterzach32.simulator

import java.awt.Dimension
import java.awt.GridLayout
import java.util.*
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSlider

class ConfigPanel : JPanel() {
    val labels = ArrayList<JLabel>()
    val sliders = ArrayList<JSlider>()

    init {
        layout = GridLayout(4, 2, 10, 10)

        labels.add(JLabel("Left Pitch: 0.0"))
        labels.add(JLabel("Left Roll: 0.0"))
        labels.add(JLabel("Right Pitch: 0.0"))
        labels.add(JLabel("Right Roll: 0.0"))

        for (i in labels.indices) {
            sliders.add(JSlider(-100, 100, 0))
            sliders[i].majorTickSpacing = 10
            sliders[i].minorTickSpacing = 5
            sliders[i].paintTicks = true
            sliders[i].snapToTicks = true

            add(sliders[i])
            add(labels[i])

            sliders[i].addChangeListener {
                val str: String
                when (i) {
                    0 -> str = "Left Pitch: "
                    1 -> str = "Left Roll: "
                    2 -> str = "Right Pitch: "
                    3 -> str = "Right Roll: "
                    else -> str = "null"
                }
                labels[i].text = "$str${sliders[i].value.toDouble()/100}"
                val window = WINDOW!!
                window.simPanel.arrows[i].magnitude = sliders[i].value/100.0
                window.simPanel.repaint()
            }
        }
    }

    override fun getPreferredSize() = Dimension(300, 300)
}