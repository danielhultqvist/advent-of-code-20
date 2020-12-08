package day08

import java.io.File

data class Result(val success: Boolean, val accumulator: Long)

fun main(args: Array<String>) {
    val instructions = File(args[0]).readLines()

    val a = run(instructions).accumulator
    val b = instructions
        .mapIndexed { i, candidate ->
            val modifiedInstructions = instructions.toMutableList()
            if (candidate.startsWith("jmp")) {
                modifiedInstructions[i] = candidate.replace("jmp", "nop")
            } else {
                modifiedInstructions[i] = candidate.replace("nop", "jmp")
            }
            run(modifiedInstructions)
        }
        .first { it.success }
        .accumulator

    println(a)
    println(b)
}

private fun run(instructions: List<String>): Result {
    val positionsVisited = mutableSetOf<Int>()
    var accumulator = 0L
    var position = 0

    while (!positionsVisited.contains(position) && 0 <= position && position < instructions.size) {
        positionsVisited.add(position)
        val instruction = instructions[position]
        val code = instruction.substringBefore(" ")
        val argument = instruction.substringAfter(" ")

        when (code) {
            "nop" -> position++
            "acc" -> {
                accumulator += argument.toInt()
                position++
            }
            "jmp" -> {
                position += argument.toInt()
            }
        }

        if (position == instructions.size) {
            return Result(true, accumulator)
        }
    }
    return Result(false, accumulator)
}
