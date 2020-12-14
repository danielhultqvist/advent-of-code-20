package day12

import java.io.File
import kotlin.math.absoluteValue

enum class Direction(val code: String, val value: Int) {
    NORTH("N", 0),
    EAST("E", 1),
    SOUTH("S", 2),
    WEST("W", 3);

    fun rotate(degrees: Int): Direction {
        val normalized = (degrees / 90) % values().size
        val newValue = (value + normalized) % values().size

        return if (newValue >= 0) {
            values()[newValue]
        } else {
            values()[values().size + newValue]
        }
    }
}

data class Instruction(val code: Char, val units: Int)

class Boat(var direction: Direction, var x: Int, var y: Int) {
    fun run(instruction: Instruction): Boat {
        when (instruction.code) {
            'N' -> {
                y -= instruction.units
            }
            'E' -> {
                x += instruction.units
            }
            'S' -> {
                y += instruction.units
            }
            'W' -> {
                x -= instruction.units
            }
            'R' -> {
                direction = direction.rotate(instruction.units)
            }
            'L' -> {
                direction = direction.rotate(-instruction.units)
            }
            'F' -> {
                when (direction) {
                    Direction.NORTH -> y -= instruction.units
                    Direction.EAST -> x += instruction.units
                    Direction.SOUTH -> y += instruction.units
                    Direction.WEST -> x -= instruction.units
                }
            }
        }
        return this
    }
}

fun main(args: Array<String>) {
    val instructions = File(args[0]).readLines().map { Instruction(it[0], it.substring(1).toInt()) }

    val boat = Boat(Direction.EAST, 0, 0)
    instructions.forEach { boat.run(it) }

    println("X: ${boat.x}, Y: ${boat.y}")
    println("Manhattan: ${boat.x.absoluteValue + boat.y.absoluteValue}")
}
