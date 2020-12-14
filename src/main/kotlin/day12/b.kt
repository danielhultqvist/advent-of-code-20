package day12b

import java.io.File
import kotlin.math.absoluteValue

data class Instruction(val code: Char, val units: Int)

class Boat(var x: Int, var y: Int, var waypointX: Int, var waypointY: Int) {
    fun run(instruction: Instruction): Boat {
        when (instruction.code) {
            'N' -> {
                waypointY -= instruction.units
            }
            'E' -> {
                waypointX += instruction.units
            }
            'S' -> {
                waypointY += instruction.units
            }
            'W' -> {
                waypointX -= instruction.units
            }
            'R' -> {
                for (i in 1..(instruction.units / 90)) {
                    val waypointXBefore = waypointX
                    val waypointYBefore = waypointY
                    waypointX = -waypointYBefore
                    waypointY = waypointXBefore
                }
            }
            'L' -> {
                for (i in 1..(instruction.units / 90)) {
                    val waypointXBefore = waypointX
                    val waypointYBefore = waypointY
                    waypointX = waypointYBefore
                    waypointY = -waypointXBefore
                }
            }
            'F' -> {
                x += waypointX * instruction.units
                y += waypointY * instruction.units
            }
        }
        return this
    }
}

fun main(args: Array<String>) {
    val instructions = File(args[0]).readLines().map { Instruction(it[0], it.substring(1).toInt()) }

    val boat = Boat(0, 0, 10, -1)
    instructions.forEach {
        println("Running $it")
        boat.run(it)
        println("X: ${boat.x}, Y: ${boat.y}. Waypoint: (${boat.waypointX},${boat.waypointY})")
        println("Manhattan: ${boat.x.absoluteValue + boat.y.absoluteValue}")
    }
}
