package net.masterzach32.simulator

import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory
import javax.swing.UIManager

val LOGGER = LoggerFactory.getLogger(Window::class.java)!!
var WINDOW: Window? = null

fun main(args: Array<String>) {
    val config = ConfigFactory.load()
    LOGGER.info("Starting Mechanum Simulator v${config.getString("version")}")
    LOGGER.info("This program is for testing and teaching the basics of mechanum drive systems.")
    var authors = ""
    config.getStringList("authors").forEach { authors += "$it, " }
    LOGGER.info("Authors: ${authors.substring(0, authors.length-2)}")
    LOGGER.info("Developed by ${config.getString("frc-team.name")} (FRC Team ${config.getString("frc-team.num")})")
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    WINDOW = Window()
}