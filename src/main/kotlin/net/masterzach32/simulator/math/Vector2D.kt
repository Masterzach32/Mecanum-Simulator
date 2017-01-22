package net.masterzach32.simulator.math

class Vector2D(val x: Double, val y: Double) {

    val magnitude = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0))

    val direction = Math.tanh(x / y)

    fun invert() = Vector2D(-x, -y)

    fun add(vector2D: Vector2D) = Vector2D(x + vector2D.x, y + vector2D.y)

    fun subtract(vector2D: Vector2D) = Vector2D(x - vector2D.x, y + vector2D.y)

    fun multiply(double: Double) = Vector2D(x * double, y * double)

    fun dot(vector2D: Vector2D) = x * vector2D.x + y * vector2D.y

}