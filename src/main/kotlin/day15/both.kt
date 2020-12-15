package day15

import java.io.File

fun main(args: Array<String>) {
    val startingNumbers = File(args[0]).readLines()[0].split(",").map { it.toLong() }

    val a = calculateLastSpoken(startingNumbers, 2020)
    val b = calculateLastSpoken(startingNumbers, 30000000)

    println("A: ${a}")
    println("B: ${b}")
}

private fun calculateLastSpoken(startingNumbers: List<Long>, lastTurn: Int): Long {
    val spokenNumbers = mutableMapOf<Long, Int>()
    startingNumbers.dropLast(1).mapIndexed { idx, number -> spokenNumbers[number] = idx }

    var lastSpoken = startingNumbers.last()
    for (i in startingNumbers.size until lastTurn) {
        val numberToSay =
            if (spokenNumbers.containsKey(lastSpoken))
                (i - 1) - spokenNumbers[lastSpoken]!!
            else
                0L

        // Remember to set the position of the last spoken number AFTER calculating what to say now
        spokenNumbers[lastSpoken] = i - 1
        // Here is where the elf would say the next number
        lastSpoken = numberToSay.toLong()
    }
    return lastSpoken
}
