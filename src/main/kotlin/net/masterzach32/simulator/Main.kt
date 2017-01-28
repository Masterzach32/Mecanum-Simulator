package net.masterzach32.simulator

import com.typesafe.config.ConfigFactory
import net.masterzach32.simulator.frame.Window
import org.slf4j.LoggerFactory
import javax.swing.UIManager

val LOGGER = LoggerFactory.getLogger(Window::class.java)!!
val CONFIG = ConfigFactory.load()
var WINDOW: Window? = null

fun main(args: Array<String>) {
    LOGGER.info("Starting Mecanum Simulator v${CONFIG.getString("version")}")
    LOGGER.info("This program is for testing and teaching the basics of mecanum drive systems.")
    var authors = ""
    CONFIG.getStringList("authors").forEach { authors += "$it, " }
    LOGGER.info("Authors: ${authors.substring(0, authors.length-2)}")
    LOGGER.info("Developed by ${CONFIG.getString("frc-team.name")} (FRC Team ${CONFIG.getString("frc-team.num")})")
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    WINDOW = Window()
}